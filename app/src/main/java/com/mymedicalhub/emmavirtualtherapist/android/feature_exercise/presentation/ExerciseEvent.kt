package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation

sealed class ExerciseEvent {
    object FetchAssessments : ExerciseEvent()
}