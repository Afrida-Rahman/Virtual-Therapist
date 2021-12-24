package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Patient

@Database(
    entities = [Patient::class],
    version = 1
)
abstract class PatientDatabase: RoomDatabase() {

    abstract val patientDao: PatientDao

    companion object {
        const val DB_NAME = "patient_db"
    }
}