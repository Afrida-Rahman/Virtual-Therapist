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
)

class InvalidPatientException(message: String) : Exception(message)
