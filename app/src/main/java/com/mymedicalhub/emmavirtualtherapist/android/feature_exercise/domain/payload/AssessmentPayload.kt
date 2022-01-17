package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.payload

data class AssessmentPayload(
    val tenant: String,
    val patientId: String
)