package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.repository

import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.data.dto.AssessmentDto
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.payload.AssessmentPayload
import retrofit2.http.Body
import retrofit2.http.POST

interface RemoteAssessmentRepository {

    @POST("/api/exercisekeypoint/GetPatientExercise")
    suspend fun fetchAssessments(@Body payload: AssessmentPayload): AssessmentDto
}