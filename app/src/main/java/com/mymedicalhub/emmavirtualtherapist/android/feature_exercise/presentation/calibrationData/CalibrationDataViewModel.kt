package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.calibrationData

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalibrationDataViewModel @Inject constructor() : ViewModel() {
    private val _hipToKneeDistance = mutableStateOf("")
    val hipToKneeDistance: State<String> = _hipToKneeDistance

    fun onEvent(event: CalibrationDataEvent) {
        when (event) {
            is CalibrationDataEvent.EnteredhipToKneeDistance -> {
                _hipToKneeDistance.value = event.hipToKneeDistance
            }
        }
    }
}


