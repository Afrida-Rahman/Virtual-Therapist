package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.usecase

import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Patient
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository.LocalPatientRepository

class GetLoggedInPatient(
    private val repositoryPatientRepository: LocalPatientRepository
) {

    suspend operator fun invoke(): Patient? {
        return repositoryPatientRepository.getLoggedInPatient()
    }
}