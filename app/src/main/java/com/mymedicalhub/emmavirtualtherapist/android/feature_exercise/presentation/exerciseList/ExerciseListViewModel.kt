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
    private val preferences: SharedPreferences
) : ViewModel() {
    private val _patient = Utilities.getPatient(preferences)
    val patient: Patient = _patient

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

    fun onEvent(event: ExerciseEvent) {
        when (event) {
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
            is ExerciseEvent.SaveDataButtonClicked -> {
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
            else -> {}
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