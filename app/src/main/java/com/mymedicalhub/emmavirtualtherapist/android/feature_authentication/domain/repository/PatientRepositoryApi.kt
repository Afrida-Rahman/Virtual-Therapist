package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository

import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.data.dto.PatientDto

interface PatientRepositoryApi {

    suspend fun patientLogIn(tenant: String, email: String, password: String): PatientDto
}