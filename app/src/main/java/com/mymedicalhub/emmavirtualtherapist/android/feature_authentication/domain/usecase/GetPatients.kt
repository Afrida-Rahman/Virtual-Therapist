package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.usecase

import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Patient
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository.PatientRepository
import kotlinx.coroutines.flow.Flow

class GetPatients(
    private val repository: PatientRepository
) {

    operator fun invoke(): Flow<List<Patient>> {
        return repository.getPatients()
    }
}
