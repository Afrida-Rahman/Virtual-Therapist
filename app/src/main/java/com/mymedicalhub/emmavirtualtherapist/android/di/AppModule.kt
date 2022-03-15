package com.mymedicalhub.emmavirtualtherapist.android.di

import android.app.Application
import androidx.room.Room
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Urls
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.data.data_source.PatientDatabase
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.data.repository.LocalPatientRepositoryImpl
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository.LocalPatientRepository
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.repository.RemotePatientRepository
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.usecase.*
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.repository.RemoteAssessmentRepository
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.repository.RemoteExerciseTrackingRepository
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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
    fun providesPatientRepositoryLocal(db: PatientDatabase): LocalPatientRepository {
        return LocalPatientRepositoryImpl(db.patientDao)
    }

    @Provides
    @Singleton
    fun providesRemotePatientRepository(): RemotePatientRepository {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Urls.get("emma"))
            .build()
            .create(RemotePatientRepository::class.java)
    }

    @Provides
    @Singleton
    fun providesPatientUseCases(
        repositoryPatientRepository: LocalPatientRepository,
        remote: RemotePatientRepository
    ): PatientUseCases {
        return PatientUseCases(
            getLoggedInPatient = GetLoggedInPatient(repositoryPatientRepository),
            getPatients = GetPatients(repositoryPatientRepository),
            insertPatient = InsertPatient(repositoryPatientRepository),
            deletePatient = DeletePatient(repositoryPatientRepository),
            patientInformation = PatientInformation(remote)
        )
    }

    @Provides
    @Singleton
    fun providesRemoteAssessmentRepository(): RemoteAssessmentRepository {
        val client = OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Urls.get("emma"))
            .client(client)
            .build()
            .create(RemoteAssessmentRepository::class.java)
    }

    @Provides
    @Singleton
    fun providesRemoteExerciseTrackingRepository(): RemoteExerciseTrackingRepository {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.injurycloud.com")
            .build()
            .create(RemoteExerciseTrackingRepository::class.java)
    }

    @Provides
    @Singleton
    fun providesExerciseUseCases(
        remoteAssessmentRepository: RemoteAssessmentRepository,
        remoteExerciseTrackingRepository: RemoteExerciseTrackingRepository
    ): ExerciseUseCases {
        return ExerciseUseCases(
            fetchAssessments = FetchAssessments(repository = remoteAssessmentRepository),
            fetchExercises = FetchExercises(repository = remoteAssessmentRepository),
            fetchExerciseConstraints = FetchExerciseConstraints(repository = remoteAssessmentRepository),
            saveExerciseData = SaveExerciseData(repository = remoteExerciseTrackingRepository)
        )
    }
}
