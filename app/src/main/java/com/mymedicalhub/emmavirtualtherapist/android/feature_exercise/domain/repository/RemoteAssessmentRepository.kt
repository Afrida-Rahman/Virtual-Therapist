package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.repository

import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.data.dto.AssessmentDto
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.data.dto.ExerciseListDto
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.data.dto.PhaseDto
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.payload.AssessmentPayload
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.payload.ExerciseConstraintPayload
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.payload.ExerciseListPayload
import retrofit2.http.Body
import retrofit2.http.POST

interface RemoteAssessmentRepository {

    @POST("/api/exercisekeypoint/GetPatientAssessments")
    suspend fun fetchAssessments(@Body payload: AssessmentPayload): AssessmentDto

    @POST("/api/exercisekeypoint/GetPatientExercises")
    suspend fun fetchExercises(@Body payload: ExerciseListPayload): ExerciseListDto

    @POST("/api/exercisekeypoint/GetPatientExerciseRestrictions")
    suspend fun fetchExerciseConstraints(@Body payload: ExerciseConstraintPayload): PhaseDto
}