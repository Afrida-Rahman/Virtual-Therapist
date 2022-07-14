package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.calibrationData

sealed class CalibrationDataEvent {
    data class EnteredShoulderToShoulderDistance(val ShoulderToShoulderDistance: String) :
        CalibrationDataEvent()

    data class EnteredShoulderToElbowDistance(val ShoulderToElbowDistance: String) :
        CalibrationDataEvent()

    data class EnteredElbowToWristDistance(val ElbowToWristDistance: String) :
        CalibrationDataEvent()

    class SaveDataButtonClick(val onSuccess: () -> Unit) : CalibrationDataEvent()
}