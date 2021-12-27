package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.usecase

import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Patient
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository.PatientRepositoryLocal
import kotlinx.coroutines.flow.Flow

class GetPatients(
    private val repository: PatientRepositoryLocal
) {

    operator fun invoke(): Flow<List<Patient>> {
        return repository.getPatients()
    }
}
