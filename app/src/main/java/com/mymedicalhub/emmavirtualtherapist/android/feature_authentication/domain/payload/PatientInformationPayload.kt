package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.payload

data class PatientInformationPayload(
    val email: String,
    val password: String,
    val tenant: String
)