package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.data.dto

import com.google.gson.annotations.SerializedName
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Assessment

data class RemoteAssessment(
    @SerializedName("BodyRegionId")
    val bodyRegionId: Int,
    @SerializedName("BodyRegionName")
    val bodyRegionName: String,
    @SerializedName("CreatedOnUtc")
    val createdOnUtc: String,
    @SerializedName("Exercises")
    val remoteExercises: List<RemoteExercise>,
    @SerializedName("IsReportReady")
    val isReportReady: Boolean,
    @SerializedName("ProviderId")
    val providerId: String,
    @SerializedName("ProviderName")
    val providerName: String,
    @SerializedName("RegistrationType")
    val registrationType: String,
    @SerializedName("TestId")
    val testId: String
)

fun RemoteAssessment.toAssessment(): Assessment {
    return Assessment(
        testId = testId,
        creationDate = createdOnUtc,
        isReportReady = isReportReady,
        providerId = providerId,
        providerName = providerName,
        bodyRegionId = bodyRegionId,
        bodyRegionName = bodyRegionName,
        registrationType = registrationType,
        exercises = remoteExercises.map { it.toExercise() },
    )
}