package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Patient(
    @PrimaryKey val id: Int? = null,
    val tenant: String,
    val patientId: String,
    val firstName: String,
    val lastName: String,
    val loggedIn: Boolean = false
) {
    companion object {
        const val FIRST_NAME = "firstName"
        const val LAST_NAME = "lastName"
        const val PATIENT_ID = "patientId"
        const val TENANT = "tenant"
    }
}

class InvalidPatientException(message: String) : Exception(message)
