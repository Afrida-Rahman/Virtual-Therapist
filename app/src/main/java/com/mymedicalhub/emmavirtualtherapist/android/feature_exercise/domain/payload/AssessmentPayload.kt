package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.payload

import com.google.gson.annotations.SerializedName

data class AssessmentPayload(
    @SerializedName("Tenant") val tenant: String,
    @SerializedName("PatientId") val patientId: String
)