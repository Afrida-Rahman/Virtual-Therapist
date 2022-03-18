package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.usecase

import com.mymedicalhub.emmavirtualtherapist.android.core.Resource
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.data.dto.toAssessmentList
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Assessment
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.payload.AssessmentPayload
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.repository.RemoteAssessmentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FetchAssessments @Inject constructor(
    private val repository: RemoteAssessmentRepository
) {
    operator fun invoke(
        tenant: String,
        patientId: String
    ): Flow<Resource<List<Assessment>>> = flow {
        emit(Resource.Loading())
        try {
            val assessmentDto = repository.fetchAssessments(
                payload = AssessmentPayload(
                    tenant = tenant,
                    patientId = patientId
                )
            )
            emit(
                Resource.Success(
                    assessmentDto.toAssessmentList().sortedBy { it.testId }
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error("Could not reach to the server"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred!"))
        }
    }
}