package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model

data class Exercise(
    val id: Int,
    val name: String,
    val imageURLs: List<String>,
    val videoURL: String?,
    val frequency: Int,
    val repetition: Int,
    val set: Int,
    val protocolId: Int,
    val instruction: String,
    val phases: List<Phase>
)