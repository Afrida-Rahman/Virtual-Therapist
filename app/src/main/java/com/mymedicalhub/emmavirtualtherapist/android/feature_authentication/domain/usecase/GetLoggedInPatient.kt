package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.usecase

import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Patient
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository.PatientRepositoryLocal

class GetLoggedInPatient(
    private val repository: PatientRepositoryLocal
) {

    suspend operator fun invoke(): Patient? {
        return repository.getLoggedInPatient()
    }
}