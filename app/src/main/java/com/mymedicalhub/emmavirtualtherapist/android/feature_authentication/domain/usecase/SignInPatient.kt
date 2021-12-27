package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.usecase

import com.mymedicalhub.emmavirtualtherapist.android.core.Resource
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.data.dto.toPatient
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Patient
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository.PatientRepositoryApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SignInPatient @Inject constructor(
    private val api: PatientRepositoryApi
) {

    suspend operator fun invoke(
        email: String,
        password: String,
        tenant: String
    ): Flow<Resource<Patient>> = flow {
        try {
            emit(Resource.Loading())
            val patient = api.patientLogIn(tenant = tenant, email = email, password = password)
                .toPatient(tenant)
            emit(Resource.Success(patient))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred!"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach to the server."))
        }
    }
}