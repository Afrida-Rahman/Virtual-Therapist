package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model

data class Assessment(
    val testId: String,
    val creationDate: String,
    val isReportReady: Boolean,
    val providerId: String,
    val providerName: String,
    val bodyRegionId: Int,
    val bodyRegionName: String,
    val exercises: List<Exercise>
)