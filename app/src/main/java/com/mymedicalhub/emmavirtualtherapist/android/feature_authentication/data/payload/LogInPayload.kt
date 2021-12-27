package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.data.payload

data class LogInPayload(
    val email: String,
    val password: String,
    val tenant: String
)
