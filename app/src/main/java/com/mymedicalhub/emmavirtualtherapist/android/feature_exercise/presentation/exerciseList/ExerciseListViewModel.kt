package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exerciseList

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mymedicalhub.emmavirtualtherapist.android.core.Resource
import com.mymedicalhub.emmavirtualtherapist.android.core.UIEvent
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Utilities
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Exercise
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.usecase.ExerciseUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseListViewModel @Inject constructor(
    private val exerciseUseCases: ExerciseUseCases,
    preferences: SharedPreferences
) : ViewModel() {
    private val patient = Utilities.getPatient(preferences)

    private val _showExerciseFilter = mutableStateOf(false)
    val showExerciseFilter: State<Boolean> = _showExerciseFilter

    private val _searchTerm = mutableStateOf("")
    val searchTerm: State<String> = _searchTerm

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

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: ExerciseListEvent) {
        when (event) {
            is ExerciseListEvent.ToggleExerciseFilter -> {
                _showExerciseFilter.value = !showExerciseFilter.value
            }
            is ExerciseListEvent.ExerciseNameEntered -> {
                _searchTerm.value = event.name
            }
            is ExerciseListEvent.ManualSelectedExerciseId -> {
                _manualSelectedExercise.value = event.exerciseId
            }
            is ExerciseListEvent.ManualRepetitionCountEntered -> {
                _manualRepetitionCount.value = event.value
            }
            is ExerciseListEvent.ManualSetCountEntered -> {
                _manualSetCount.value = event.value
            }
            is ExerciseListEvent.ManualWrongCountEntered -> {
                _manualWrongCount.value = event.value
            }
            is ExerciseListEvent.ShowManualTrackingAlertDialogue -> {
                _showManualTrackingForm.value = true
            }
            is ExerciseListEvent.HideManualTrackingAlertDialogue -> {
                _showManualTrackingForm.value = false
                _manualRepetitionCount.value = ""
                _manualSetCount.value = ""
                _manualWrongCount.value = ""
            }
            is ExerciseListEvent.ShowExerciseDemo -> {
                _manualSelectedExercise.value = event.exerciseId
                _showExerciseDemo.value = true
            }
            is ExerciseListEvent.HideExerciseDemo -> {
                _showExerciseDemo.value = false
            }
            is ExerciseListEvent.SaveDataButtonClicked -> {
                if (!saveDataButtonClicked.value) {
                    _saveDataButtonClicked.value = true
                    patient.let {
                        saveExerciseData(
                            tenant = it.tenant,
                            testId = event.testId,
                            patientId = it.patientId,
                            exercise = event.exercise
                        )
                    }
                }
            }
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