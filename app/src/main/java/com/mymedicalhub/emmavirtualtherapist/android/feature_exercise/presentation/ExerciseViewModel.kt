package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mymedicalhub.emmavirtualtherapist.android.core.Resource
import com.mymedicalhub.emmavirtualtherapist.android.core.UIEvent
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Assessment
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Exercise
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
class ExerciseViewModel @Inject constructor(
    private val exerciseUseCases: ExerciseUseCases
) : ViewModel() {
    private val _assessments = mutableStateOf<List<Assessment>>(emptyList())
    val assessments: State<List<Assessment>> = _assessments

    private val _exercises = mutableStateOf<List<Exercise>?>(null)
    val exercises: State<List<Exercise>?> = _exercises

    private val _isAssessmentLoading = mutableStateOf(false)
    val isAssessmentLoading: State<Boolean> = _isAssessmentLoading

    private val _showTryAgainButton = mutableStateOf(false)
    val showTryAgain: State<Boolean> = _showTryAgainButton

    private val _showSearchBar = mutableStateOf(false)
    val showSearchBar: State<Boolean> = _showSearchBar

    private val _searchTerm = mutableStateOf("")
    val searchTerm: State<String> = _searchTerm

    private val _showManualTrackingForm = mutableStateOf(false)
    val showManualTrackingForm: State<Boolean> = _showManualTrackingForm

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

    private val _showFrontCamera = mutableStateOf(true)
    val showFrontCamera: State<Boolean> = _showFrontCamera

    private var searchCoroutine: Job? = null

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        fetchAssessments()
    }

    fun onEvent(event: ExerciseEvent) {
        when (event) {
            is ExerciseEvent.FetchAssessments -> fetchAssessments()
            is ExerciseEvent.SearchTermEntered -> {
                _searchTerm.value = event.searchTerm
                searchExercises(event.testId, event.searchTerm)
            }
            is ExerciseEvent.ShowSearchBar -> {
                _showSearchBar.value = true
            }
            is ExerciseEvent.HideSearchBar -> {
                _showSearchBar.value = false
                _searchTerm.value = ""
            }
            is ExerciseEvent.FlipCamera -> {
                _showFrontCamera.value = !showFrontCamera.value
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
            is ExerciseEvent.SaveDataButtonClicked -> {
                if (!saveDataButtonClicked.value) {
                    _saveDataButtonClicked.value = true
                    saveExerciseData(
                        tenant = "emma",
                        testId = event.testId,
                        patientId = "0fc2e143-5abd-eb11-8236-000d3a33d0fd",
                        exercise = event.exercise
                    )
                }
            }
        }
    }

    fun getExercise(testId: String, exerciseId: Int): Exercise? {
        return getExercises(testId = testId).find { it.id == exerciseId }
    }

    fun searchExercises(testId: String, searchTerm: String = "") {
        searchCoroutine?.cancel()
        searchCoroutine = viewModelScope.launch {
            delay(500L)
            _exercises.value = getExercises(testId = testId, searchTerm = searchTerm)
        }
    }

    private fun getExercises(testId: String, searchTerm: String = ""): List<Exercise> {
        var exercises: List<Exercise> = emptyList()
        _assessments.value.find { it.testId == testId }?.let {
            exercises = it.exercises
        }
        if (searchTerm.isNotEmpty()) {
            exercises = exercises.filter { it.name.lowercase().startsWith(searchTerm.lowercase()) }
        }
        return exercises.sortedBy { it.name }
    }

    private fun fetchAssessments() {
        viewModelScope.launch {
            exerciseUseCases.fetchAssessments("emma", "0fc2e143-5abd-eb11-8236-000d3a33d0fd")
                .onEach {
                    when (it) {
                        is Resource.Error -> {
                            _showTryAgainButton.value = true
                            _isAssessmentLoading.value = false
                            _eventFlow.emit(
                                UIEvent.ShowSnackBar(
                                    it.message ?: "Failed to load assessments! Please try again."
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
                            _assessments.value = it.data ?: emptyList()
                        }
                    }
                }.launchIn(this)
        }
    }

    private fun saveExerciseData(
        tenant: String,
        testId: String,
        patientId: String,
        exercise: Exercise
    ) {
        viewModelScope.launch {
            when {
                manualRepetitionCount.value.isBlank() -> {
                    _saveDataButtonClicked.value = false
                    _eventFlow.emit(UIEvent.ShowToastMessage("Repetition count cannot be blank"))
                }
                manualSetCount.value.isBlank() -> {
                    _saveDataButtonClicked.value = false
                    _eventFlow.emit(UIEvent.ShowToastMessage("Set count cannot be blank"))
                }
                manualWrongCount.value.isBlank() -> {
                    _saveDataButtonClicked.value = false
                    _eventFlow.emit(UIEvent.ShowToastMessage("Wrong count cannot be blank"))
                }
                else -> {
                    exerciseUseCases.saveExerciseData(
                        exercise = exercise,
                        testId = testId,
                        patientId = patientId,
                        noOfReps = manualRepetitionCount.value.toInt(),
                        noOfSets = manualSetCount.value.toInt(),
                        noOfWrongCount = manualWrongCount.value.toInt(),
                        tenant = tenant
                    ).onEach {
                        when (it) {
                            is Resource.Error -> {
                                _saveDataButtonClicked.value = false
                                _eventFlow.emit(
                                    UIEvent.ShowToastMessage(
                                        it.message ?: "Unknown error"
                                    )
                                )
                            }
                            is Resource.Loading -> {
                                _saveDataButtonClicked.value = true
                                _eventFlow.emit(UIEvent.ShowToastMessage("Please wait"))
                            }
                            is Resource.Success -> {
                                _showManualTrackingForm.value = false
                                _saveDataButtonClicked.value = false
                                _manualRepetitionCount.value = ""
                                _manualSetCount.value = ""
                                _manualWrongCount.value = ""
                                _manualSelectedExercise.value = 0
                                it.data?.let { exerciseTrackingResponse ->
                                    _eventFlow.emit(
                                        UIEvent.ShowToastMessage(
                                            exerciseTrackingResponse.message
                                        )
                                    )
                                }
                            }
                        }
                    }.launchIn(this)
                }
            }
        }
    }
}