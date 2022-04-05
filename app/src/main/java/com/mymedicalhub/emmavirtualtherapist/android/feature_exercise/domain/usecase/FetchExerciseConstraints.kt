package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.usecase

import com.mymedicalhub.emmavirtualtherapist.android.core.Resource
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.data.dto.toPhaseList
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Phase
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.payload.ExerciseConstraintPayload
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.repository.RemoteAssessmentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FetchExerciseConstraints @Inject constructor(
    private val repository: RemoteAssessmentRepository
) {
    operator fun invoke(
        tenant: String,
        exerciseId: Int
    ): Flow<Resource<List<Phase>>> = flow {
        emit(Resource.Loading())
        try {
            val exercisePhaseDto = repository.fetchExerciseConstraints(
                payload = ExerciseConstraintPayload(
                    tenant = tenant,
                    exerciseId = exerciseId
                )
            )
            emit(
                Resource.Success(
                    exercisePhaseDto.toPhaseList()
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error("Could not reach to the server"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred!"))
        }
    }
}