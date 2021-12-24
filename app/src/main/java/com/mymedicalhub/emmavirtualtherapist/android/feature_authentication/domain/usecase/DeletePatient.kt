package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.usecase

import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Patient
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository.PatientRepository

class DeletePatient(
    private val repository: PatientRepository
) {

    suspend operator fun invoke(patient: Patient) {
        repository.deletePatient(patient)
    }
}
