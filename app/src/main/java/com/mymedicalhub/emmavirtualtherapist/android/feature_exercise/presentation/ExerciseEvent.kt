package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation

import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Exercise

sealed class ExerciseEvent {
    object FetchAssessments : ExerciseEvent()
    object ShowAssessmentSearchBar : ExerciseEvent()
    object HideAssessmentSearchBar : ExerciseEvent()
    object ShowExerciseSearchBar : ExerciseEvent()
    object HideExerciseSearchBar : ExerciseEvent()
    object FlipCamera : ExerciseEvent()
    object GoToAssessmentPage : ExerciseEvent()
    object ShowManualTrackingAlertDialogue : ExerciseEvent()
    object HideManualTrackingAlertDialogue : ExerciseEvent()
    data class ShowExerciseDemo(val exerciseId: Int) : ExerciseEvent()
    object HideExerciseDemo : ExerciseEvent()
    data class SaveDataButtonClicked(val testId: String, val exercise: Exercise) : ExerciseEvent()
    data class ManualSelectedExerciseId(val exerciseId: Int) : ExerciseEvent()
    data class ManualRepetitionCountEntered(val value: String) : ExerciseEvent()
    data class ManualSetCountEntered(val value: String) : ExerciseEvent()
    data class ManualWrongCountEntered(val value: String) : ExerciseEvent()
    data class AssessmentSearchTermEntered(val searchTerm: String) : ExerciseEvent()
    data class ExerciseSearchTermEntered(val testId: String, val searchTerm: String) :
        ExerciseEvent()
}