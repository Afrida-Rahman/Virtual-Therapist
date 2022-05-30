package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exerciseList

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mymedicalhub.emmavirtualtherapist.android.core.Resource
import com.mymedicalhub.emmavirtualtherapist.android.core.UIEvent
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Utilities
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Patient
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Exercise
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.usecase.ExerciseUseCases
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.ExerciseEvent
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.assessmentList.AssessmentListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    private val exerciseUseCases: ExerciseUseCases,
    private val preferences: SharedPreferences
) : ViewModel() {
    private val viewModel: AssessmentListViewModel =
        AssessmentListViewModel(exerciseUseCases, preferences)

    private val _patient = Utilities.getPatient(preferences)
    val patient: Patient = _patient

    private val _exercises = mutableStateOf<List<Exercise>?>(null)
    val exercises: State<List<Exercise>?> = _exercises

    private val _isExerciseLoading = mutableStateOf(true)
    val isExerciseLoading: State<Boolean> = _isExerciseLoading

    private val _showTryAgainButton = mutableStateOf(false)
    val showTryAgain: State<Boolean> = _showTryAgainButton

    private val _showExerciseSearchBar = mutableStateOf(false)
    val showExerciseSearchBar: State<Boolean> = _showExerciseSearchBar

    private val _exerciseSearchTerm = mutableStateOf("")
    val exerciseSearchTerm: State<String> = _exerciseSearchTerm

    private val _showManualTrackingForm = mutableStateOf(false)
    val showManualTrackingForm: State<Boolean> = _showManualTrackingForm

    private val _showExerciseDemo = mutableStateOf(false)
    val showExerciseDemo: State<Boolean> = _showExerciseDemo

    private val _manualSelectedExercise = mutableStateOf(0)
    val manualSelectedExercise: State<Int> = _manualSelectedExercise

    private val _manualRepetitionCount = mutableStateOf("")
    val manualRepetitionCount: State<String> = _manualRepetitionCount

    private val _manualSetCount = mutableStateOf("")
    val manualSetCount: State<String> = _manualSetCount

    private val _manualWrongCount = mutableStateOf("")
    val manualWrongCount: State<String> = _manualWrongCount

    private val _saveDataButtonClicked = mutableStateOf(false)
    val saveDataButtonClicked: State<Boolean> = _saveDataButtonClicked

    private var searchCoroutine: Job? = null

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: ExerciseEvent) {
        when (event) {
            is ExerciseEvent.FetchExercises -> fetchExercises(
                tenant = event.tenant,
                testId = event.testId
            )
            is ExerciseEvent.SignOut -> {
                Utilities.savePatient(
                    preferences = preferences,
                    data = Patient(
                        id = null,
                        tenant = "",
                        patientId = "",
                        firstName = "",
                        lastName = "",
                        email = "",
                        loggedIn = false
                    )
                )
            }
            is ExerciseEvent.ShowExerciseSearchBar -> {
                _showExerciseSearchBar.value = true
            }
            is ExerciseEvent.HideExerciseSearchBar -> {
                _showExerciseSearchBar.value = false
                _exerciseSearchTerm.value = ""
            }
            is ExerciseEvent.ExerciseSearchTermEntered -> {
                _exerciseSearchTerm.value = event.searchTerm
                searchExercises(event.testId, event.searchTerm)
            }
            is ExerciseEvent.GoToAssessmentPage -> {
                _exercises.value = null
            }
            is ExerciseEvent.ManualSelectedExerciseId -> {
                _manualSelectedExercise.value = event.exerciseId
            }
            is ExerciseEvent.ManualRepetitionCountEntered -> {
                _manualRepetitionCount.value = event.value
            }
            is ExerciseEvent.ManualSetCountEntered -> {
                _manualSetCount.value = event.value
            }
            is ExerciseEvent.ManualWrongCountEntered -> {
                _manualWrongCount.value = event.value
            }
            is ExerciseEvent.ShowManualTrackingAlertDialogue -> {
                _showManualTrackingForm.value = true
            }
            is ExerciseEvent.HideManualTrackingAlertDialogue -> {
                _showManualTrackingForm.value = false
                _manualRepetitionCount.value = ""
                _manualSetCount.value = ""
                _manualWrongCount.value = ""
            }
            is ExerciseEvent.ShowExerciseDemo -> {
                _manualSelectedExercise.value = event.exerciseId
                _showExerciseDemo.value = true
            }
            is ExerciseEvent.HideExerciseDemo -> {
                _showExerciseDemo.value = false
            }
            else -> {}
        }
    }

    fun getExercise(testId: String, exerciseId: Int): Exercise? {
        return getExercises(testId = testId).find { it.id == exerciseId }
    }

    fun loadExercises(tenant: String, testId: String) {
        if (getExercises(testId = testId).isEmpty()) {
            fetchExercises(testId = testId, tenant = tenant)
        } else {
            searchExercises(testId = testId)
        }
    }

    private fun searchExercises(testId: String, searchTerm: String = "") {
        searchCoroutine?.cancel()
        searchCoroutine = viewModelScope.launch {
            delay(500L)
            _exercises.value = getExercises(testId = testId, searchTerm = searchTerm)
        }
    }

    private fun getExercises(testId: String, searchTerm: String = ""): List<Exercise> {
        var exercises: List<Exercise> = emptyList()
        viewModel.assessments.value.find { it.testId == testId }?.let {
            exercises = it.exercises
        }
        if (searchTerm.isNotEmpty()) {
            exercises = exercises.filter { it.name.contains(searchTerm, ignoreCase = true) }
        }
        return exercises.sortedBy { it.name }
    }

    private fun fetchExercises(tenant: String, testId: String) {
        viewModelScope.launch {
            exerciseUseCases.fetchExercises(testId = testId, tenant = tenant)
                .onEach {
                    when (it) {
                        is Resource.Error -> {
                            _isExerciseLoading.value = false
                            _showTryAgainButton.value = true
                            _eventFlow.emit(
                                UIEvent.ShowSnackBar(
                                    it.message ?: "Failed to load exercise list. Please try again."
                                )
                            )
                        }
                        is Resource.Loading -> {
                            _isExerciseLoading.value = true
                        }
                        is Resource.Success -> {
                            it.data?.let { exercises ->
                                setExerciseList(testId = testId, exercises = exercises)
                            }
                            _isExerciseLoading.value = false
                        }
                    }
                }.launchIn(this)
        }
    }

    private fun setExerciseList(testId: String, exercises: List<Exercise>) {
        for (index in 0..viewModel.assessments.value.size) {
            if (viewModel.assessments.value[index].testId == testId) {
                viewModel.assessments.value[index].exercises = exercises
                _exercises.value = exercises.sortedBy { it.name }
                break
            }
        }
    }
}