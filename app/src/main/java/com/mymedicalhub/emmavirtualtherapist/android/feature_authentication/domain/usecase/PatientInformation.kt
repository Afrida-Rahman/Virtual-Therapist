package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.usecase

import com.mymedicalhub.emmavirtualtherapist.android.core.Resource
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.data.dto.toPatient
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Patient
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.payload.PatientInformationPayload
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository.RemotePatientRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PatientInformation @Inject constructor(
    private val remote: RemotePatientRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String,
        tenant: String
    ): Flow<Resource<Patient>> = flow {
        try {
            emit(Resource.Loading())
            when {
                tenant.isEmpty() -> emit(Resource.Error("Tenant cannot be empty"))
                email.isEmpty() -> emit(Resource.Error("Email cannot be empty"))
                password.isEmpty() -> emit(Resource.Error("Password cannot be empty"))
                else -> {
                    val patientDto =
                        remote.patientInformation(
                            PatientInformationPayload(
                                tenant = tenant,
                                email = email,
                                password = password
                            )
                        )
                    if (patientDto.success) {
                        emit(Resource.Success(patientDto.toPatient(tenant)))
                    } else {
                        emit(Resource.Error("Invalid email or password"))
                    }
                }
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred!"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach to the server."))
        }
    }
}