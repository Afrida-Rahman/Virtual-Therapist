package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.usecase

import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Patient
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository.PatientRepositoryLocal

class DeletePatient(
    private val repository: PatientRepositoryLocal
) {

    suspend operator fun invoke(patient: Patient) {
        repository.deletePatient(patient)
    }
}
