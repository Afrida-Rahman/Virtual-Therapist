package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.data.dto

import com.google.gson.annotations.SerializedName

data class ExerciseTrackingDto(
    @SerializedName("Successful")
    val success: Boolean,
    @SerializedName("ChatMessage")
    val message: String
)