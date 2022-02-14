package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation

import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Exercise

sealed class ExerciseEvent {
    object FetchAssessments : ExerciseEvent()
    object ShowSearchBar : ExerciseEvent()
    object HideSearchBar : ExerciseEvent()
    object FlipCamera : ExerciseEvent()
    object GoToAssessmentPage : ExerciseEvent()
    object ShowManualTrackingAlertDialogue : ExerciseEvent()
    object HideManualTrackingAlertDialogue : ExerciseEvent()
    data class SaveDataButtonClicked(val testId: String, val exercise: Exercise) : ExerciseEvent()
    data class ManualSelectedExerciseId(val exerciseId: Int) : ExerciseEvent()
    data class ManualRepetitionCountEntered(val value: String) : ExerciseEvent()
    data class ManualSetCountEntered(val value: String) : ExerciseEvent()
    data class ManualWrongCountEntered(val value: String) : ExerciseEvent()
    data class SearchTermEntered(val testId: String, val searchTerm: String) : ExerciseEvent()
}