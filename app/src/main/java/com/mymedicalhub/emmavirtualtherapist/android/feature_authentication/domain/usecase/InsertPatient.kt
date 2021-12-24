package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.usecase

import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.InvalidPatientException
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Patient
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository.PatientRepository

class InsertPatient(
    private val repository: PatientRepository
) {

    @Throws(InvalidPatientException::class)
    suspend operator fun invoke(patient: Patient) {
        if (patient.name.isBlank()) {
            throw InvalidPatientException("Patient's name cannot be empty")
        }
        if (patient.patientId.isBlank()){
            throw InvalidPatientException("Patient's ID cannot be empty")
        }
        if (patient.tenant.isBlank()) {
            throw InvalidPatientException("Patient's tenant cannot be empty")
        }
        repository.insertPatient(patient)
    }
}