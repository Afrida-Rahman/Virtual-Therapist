package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.calibrationData

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalibrationDataViewModel @Inject constructor() : ViewModel() {
    private val _shoulderToShoulderDistance = mutableStateOf("")
    val shoulderToShoulderDistance: State<String> = _shoulderToShoulderDistance

    private val _shoulderToElbowDistance = mutableStateOf("")
    val shoulderToElbowDistance: State<String> = _shoulderToElbowDistance

    private val _elbowToWristDistance = mutableStateOf("")
    val elbowToWristDistance: State<String> = _elbowToWristDistance


    fun onEvent(event: CalibrationDataEvent) {
        when (event) {
            is CalibrationDataEvent.EnteredShoulderToShoulderDistance -> {
                _shoulderToShoulderDistance.value = event.ShoulderToShoulderDistance
            }
            is CalibrationDataEvent.EnteredShoulderToElbowDistance -> {
                _shoulderToElbowDistance.value = event.ShoulderToElbowDistance
            }
            is CalibrationDataEvent.EnteredElbowToWristDistance -> {
                _elbowToWristDistance.value = event.ElbowToWristDistance
            }
            else -> {}
        }
    }
}


