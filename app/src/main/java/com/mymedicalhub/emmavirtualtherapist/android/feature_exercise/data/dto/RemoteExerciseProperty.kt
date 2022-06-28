package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.data.dto

import com.google.gson.annotations.SerializedName

data class RemoteExerciseProperty(
    @SerializedName("Id") val id: Int,
    @SerializedName("DayNumber") val dayNumber: Int,
    @SerializedName("DayName") val dayName: String,
    @SerializedName("HoldInSeconds") val holdTime: Int,
    @SerializedName("RepetitionInCount") val repetitionPerSet: Int,
    @SerializedName("SetInCount") val totalSet: Int,
    @SerializedName("FrequencyInDay") val exerciseFrequency: Int
)