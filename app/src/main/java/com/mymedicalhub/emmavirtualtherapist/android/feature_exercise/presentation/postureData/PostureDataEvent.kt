package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.postureData

sealed class PostureDataEvent {
    data class EnteredShoulderToShoulderDistance(val ShoulderToShoulderDistance: String) :
        PostureDataEvent()

    data class EnteredShoulderToElbowDistance(val ShoulderToElbowDistance: String) :
        PostureDataEvent()

    data class EnteredElbowToWristDistance(val ElbowToWristDistance: String) : PostureDataEvent()
    class SaveDataButtonClick(val onSuccess: () -> Unit) : PostureDataEvent()
}