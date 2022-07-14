package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model

data class CalibrationData(
    val shoulderToShoulderDistance: Int,
    val shoulderToElbowDistance: Int,
    val elbowToWristDistance: Int
)