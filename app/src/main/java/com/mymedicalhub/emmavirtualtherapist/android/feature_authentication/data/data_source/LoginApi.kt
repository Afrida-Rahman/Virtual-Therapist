package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.data.data_source

import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.data.payload.LogInPayload
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("/login")
    suspend fun patientLogin(@Body payload: LogInPayload): PatientDao
}