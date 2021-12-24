package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.data.repository

import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.data.data_source.PatientDao
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Patient
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository.PatientRepository
import kotlinx.coroutines.flow.Flow

class PatientRepositoryImpl(
    private val dao: PatientDao
) : PatientRepository {
    override fun getPatients(): Flow<List<Patient>> {
        return dao.getPatients()
    }

    override suspend fun getLoggedInPatient(): Patient? {
        return dao.getLoggedInPatient()
    }

    override suspend fun insertPatient(patient: Patient) {
        dao.insertPatient(patient)
    }

    override suspend fun deletePatient(patient: Patient) {
        dao.deletePatient(patient)
    }
}