package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository

import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.data.dto.PatientDto
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.payload.PatientInformationPayload
import retrofit2.http.Body
import retrofit2.http.POST

interface RemotePatientRepository {

    @POST("/api/Account/GetCrmContact")
    suspend fun patientInformation(@Body payload: PatientInformationPayload): PatientDto
}