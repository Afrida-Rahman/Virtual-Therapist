package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.data.dto

import com.google.gson.annotations.SerializedName
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Assessment

data class AssessmentDto(
    @SerializedName("Assessments")
    val remoteAssessments: List<RemoteAssessment>,
    @SerializedName("Tenant")
    val tenant: String
)

fun AssessmentDto.toAssessmentList(): List<Assessment> = remoteAssessments.map {
    it.toAssessment()
}