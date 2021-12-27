package com.mymedicalhub.emmavirtualtherapist.android.di

import android.app.Application
import androidx.room.Room
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Urls
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.data.data_source.PatientDatabase
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.data.repository.PatientRepositoryLocalImpl
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository.PatientRepositoryApi
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository.PatientRepositoryLocal
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun providesPatientRepositoryLocal(db: PatientDatabase): PatientRepositoryLocal {
        return PatientRepositoryLocalImpl(db.patientDao)
    }

    @Provides
    @Singleton
    fun providesPatientRepositoryApi(tenant: String): PatientRepositoryApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Urls.get(tenant))
            .build()
            .create(PatientRepositoryApi::class.java)
    }

    @Provides
    @Singleton
    fun providesPatientUseCases(
        repository: PatientRepositoryLocal,
        api: PatientRepositoryApi
    ): PatientUseCases {
        return PatientUseCases(
            getLoggedInPatient = GetLoggedInPatient(repository),
            getPatients = GetPatients(repository),
            insertPatient = InsertPatient(repository),
            deletePatient = DeletePatient(repository),
            signInPatient = SignInPatient(api)
        )
    }
}
