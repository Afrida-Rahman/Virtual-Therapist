package com.mymedicalhub.emmavirtualtherapist.android.di

import android.app.Application
import androidx.room.Room
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.data.data_source.PatientDatabase
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.data.repository.PatientRepositoryImpl
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository.PatientRepository
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesPatientDatabase(application: Application): PatientDatabase {
        return Room.databaseBuilder(
            application,
            PatientDatabase::class.java,
            PatientDatabase.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesPatientRepository(db: PatientDatabase): PatientRepository {
        return PatientRepositoryImpl(db.patientDao)
    }

    @Provides
    @Singleton
    fun providesPatientUseCases(repository: PatientRepository): PatientUseCases {
        return PatientUseCases(
            getLoggedInPatient = GetLoggedInPatient(repository),
            getPatients = GetPatients(repository),
            insertPatient = InsertPatient(repository),
            deletePatient = DeletePatient(repository),
            signInPatient = SignInPatient()
        )
    }
}
