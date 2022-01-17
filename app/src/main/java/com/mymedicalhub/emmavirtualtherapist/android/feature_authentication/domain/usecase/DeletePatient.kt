package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.usecase

import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Patient
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository.LocalPatientRepository

class DeletePatient(
    private val repositoryPatientRepository: LocalPatientRepository
) {

    suspend operator fun invoke(patient: Patient) {
        repositoryPatientRepository.deletePatient(patient)
    }
}
