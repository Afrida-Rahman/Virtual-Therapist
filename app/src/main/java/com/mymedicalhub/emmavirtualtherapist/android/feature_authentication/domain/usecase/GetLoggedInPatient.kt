package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.usecase

import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Patient
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository.PatientRepository

class GetLoggedInPatient(
    private val repository: PatientRepository
) {

    suspend operator fun invoke(): Patient? {
        return repository.getLoggedInPatient()
    }
}