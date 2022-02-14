package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.usecase

data class PatientUseCases(
    val getLoggedInPatient: GetLoggedInPatient,
    val getPatients: GetPatients,
    val insertPatient: InsertPatient,
    val deletePatient: DeletePatient,
    val patientInformation: PatientInformation
)
