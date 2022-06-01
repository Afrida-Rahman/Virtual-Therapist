package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mymedicalhub.emmavirtualtherapist.android.core.Resource
import com.mymedicalhub.emmavirtualtherapist.android.core.UIEvent
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Utilities
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Patient
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Assessment
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Exercise
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Phase
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.usecase.ExerciseUseCases
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
class CommonViewModel @Inject constructor(
    private val exerciseUseCases: ExerciseUseCases,
    private val preferences: SharedPreferences
) : ViewModel() {
    private var originalAssessmentList: List<Assessment> = emptyList()

    private val _assessments = mutableStateOf<List<Assessment>>(emptyList())
    val assessments: State<List<Assessment>> = _assessments

    private val _patient = Utilities.getPatient(preferences)
    var patient: Patient = _patient

    private val _exercises = mutableStateOf<List<Exercise>?>(null)
    val exercises: State<List<Exercise>?> = _exercises

    private val _isAssessmentLoading = mutableStateOf(false)
    val isAssessmentLoading: State<Boolean> = _isAssessmentLoading

    private val _isExerciseLoading = mutableStateOf(true)
    val isExerciseLoading: State<Boolean> = _isExerciseLoading

    private val _showTryAgainButton = mutableStateOf(false)
    val showTryAgain: State<Boolean> = _showTryAgainButton

    private val _showAssessmentSearchBar = mutableStateOf(false)
    val showAssessmentSearchBar: State<Boolean> = _showAssessmentSearchBar

    private val _showExerciseSearchBar = mutableStateOf(false)
    val showExerciseSearchBar: State<Boolean> = _showExerciseSearchBar

    private val _assessmentSearchTerm = mutableStateOf("")
    val assessmentSearchTerm: State<String> = _assessmentSearchTerm

    private val _exerciseSearchTerm = mutableStateOf("")
    val exerciseSearchTerm: State<String> = _exerciseSearchTerm

    private val _showFrontCamera = mutableStateOf(true)
    val showFrontCamera: State<Boolean> = _showFrontCamera

    private var searchCoroutine: Job? = null

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        patient = Utilities.getPatient(preferences = preferences)
        fetchAssessments(patient)
    }

    fun onEvent(event: ExerciseEvent) {
        when (event) {
            is ExerciseEvent.FetchAssessments -> {
                fetchAssessments(patient)
            }
            is ExerciseEvent.AssessmentSearchTermEntered -> {
                _assessmentSearchTerm.value = event.searchTerm
                _assessments.value = getAssessments(event.searchTerm)
            }
            is ExerciseEvent.ShowAssessmentSearchBar -> {
                _showAssessmentSearchBar.value = true
            }
            is ExerciseEvent.HideAssessmentSearchBar -> {
                _showAssessmentSearchBar.value = false
                _assessmentSearchTerm.value = ""
                _assessments.value = originalAssessmentList
            }
            is ExerciseEvent.FetchExercises -> fetchExercises(
                tenant = event.tenant,
                testId = event.testId
            )
            is ExerciseEvent.FetchExerciseConstraints -> fetchExerciseConstraints(
                tenant = event.tenant,
                testId = event.testId,
                exerciseId = event.exerciseId
            )
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
            is ExerciseEvent.FlipCamera -> {
                _showFrontCamera.value = !showFrontCamera.value
            }
            is ExerciseEvent.GoToAssessmentPage -> {
                _exercises.value = null
            }
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

    fun loadExerciseConstraints(tenant: String, testId: String, exerciseId: Int) {
        getExercise(testId = testId, exerciseId = exerciseId)?.let { exercise ->
            if (exercise.phases.isEmpty()) {
                fetchExerciseConstraints(tenant = tenant, testId = testId, exerciseId = exerciseId)
            }
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
        Log.d(
            "InNavigation",
            "assessment-$originalAssessmentList"
        )
        originalAssessmentList.find { it.testId == testId }?.let {
            exercises = it.exercises
            Log.d(
                "InNavigation",
                "for get exercise--$testId -- ${exercises}"
            )
        }
        if (searchTerm.isNotEmpty()) {
            exercises = exercises.filter { it.name.contains(searchTerm, ignoreCase = true) }
        }
        return exercises.sortedBy { it.name }
    }

    private fun getAssessments(searchTerm: String = ""): List<Assessment> {
        var assessments: List<Assessment> = originalAssessmentList
        if (searchTerm.isNotEmpty()) {
            assessments =
                originalAssessmentList.filter { it.testId.contains(searchTerm, ignoreCase = true) }
        }
        return assessments
    }

    private fun fetchAssessments(patient: Patient) {
        viewModelScope.launch {
            exerciseUseCases.fetchAssessments(
                tenant = patient.tenant,
                patientId = patient.patientId
            ).onEach {
                when (it) {
                    is Resource.Error -> {
                        _showTryAgainButton.value = true
                        _isAssessmentLoading.value = false
                        _eventFlow.emit(
                            UIEvent.ShowSnackBar(
                                it.message
                                    ?: "Failed to load assessments_outlined! Please try again."
                            )
                        )
                    }
                    is Resource.Loading -> {
                        _isAssessmentLoading.value = true
                        _showTryAgainButton.value = false
                    }
                    is Resource.Success -> {
                        _showTryAgainButton.value = false
                        _isAssessmentLoading.value = false
                        originalAssessmentList = it.data ?: emptyList()
                        _assessments.value = originalAssessmentList
                    }
                }
            }.launchIn(this)
        }
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

    private fun fetchExerciseConstraints(tenant: String, testId: String, exerciseId: Int) {
        viewModelScope.launch {
            exerciseUseCases.fetchExerciseConstraints(tenant = tenant, exerciseId = exerciseId)
                .onEach {
                    when (it) {
                        is Resource.Error -> {
                            _isExerciseLoading.value = false
                            _showTryAgainButton.value = true
                            _eventFlow.emit(
                                UIEvent.ShowSnackBar(
                                    it.message
                                        ?: "Failed to load exercise constraints. Please try again."
                                )
                            )
                        }
                        is Resource.Loading -> {
                            _isExerciseLoading.value = true
                        }
                        is Resource.Success -> {
                            it.data?.let { phases ->
                                setExerciseConstraints(
                                    testId = testId,
                                    exerciseId = exerciseId,
                                    phases = phases
                                )
                            }
                            _isExerciseLoading.value = false
                        }
                    }
                }.launchIn(this)
        }
    }

    private fun setExerciseList(testId: String, exercises: List<Exercise>) {
        for (index in 0.._assessments.value.size) {
            if (_assessments.value[index].testId == testId) {
                _assessments.value[index].exercises = exercises
                _exercises.value = exercises.sortedBy { it.name }
                break
            }
        }
    }

    private fun setExerciseConstraints(testId: String, exerciseId: Int, phases: List<Phase>) {
        for (index1 in 0.._assessments.value.size) {
            if (_assessments.value[index1].testId == testId) {
                for (index2 in 0.._assessments.value[index1].exercises.size) {
                    if (_assessments.value[index1].exercises[index2].id == exerciseId) {
                        _assessments.value[index1].exercises[index2].phases = phases
                        break
                    }
                }
                break
            }
        }
    }
}