package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.calibrationData

sealed class CalibrationDataEvent {
    data class EnteredhipToKneeDistance(val hipToKneeDistance: String) :
        CalibrationDataEvent()
}
