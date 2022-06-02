package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exercise

sealed class ExerciseEvent {
    object FlipCamera : ExerciseEvent()
    object GoToAssessmentPage : ExerciseEvent()
}