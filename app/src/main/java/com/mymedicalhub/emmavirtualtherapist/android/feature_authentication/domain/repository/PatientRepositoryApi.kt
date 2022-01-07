package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository

import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.data.dto.PatientDto
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.data.payload.LogInPayload
import retrofit2.http.Body
import retrofit2.http.POST

interface PatientRepositoryApi {

    @POST("/api/Account/GetCrmContact")
    suspend fun patientLogIn(@Body payload: LogInPayload): PatientDto
}