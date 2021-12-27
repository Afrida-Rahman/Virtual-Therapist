package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.data.repository

import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.data.dto.PatientDto
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository.PatientRepositoryApi
import javax.inject.Inject

class PatientRepositoryApiImpl @Inject constructor(
    private val api: PatientRepositoryApi
) : PatientRepositoryApi {
    override suspend fun patientLogIn(tenant: String, email: String, password: String): PatientDto {
        TODO("Not yet implemented")
    }
}