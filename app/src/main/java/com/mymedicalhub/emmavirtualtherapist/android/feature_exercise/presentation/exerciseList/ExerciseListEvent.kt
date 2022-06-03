package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exerciseList

import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Exercise

sealed class ExerciseListEvent {
    data class FetchExercises(val testId: String, val tenant: String) : ExerciseListEvent()
    data class FetchExerciseConstraints(
        val tenant: String,
        val testId: String,
        val exerciseId: Int
    ) : ExerciseListEvent()

    object SignOut : ExerciseListEvent()
    object ShowExerciseSearchBar : ExerciseListEvent()
    object HideExerciseSearchBar : ExerciseListEvent()
    object ShowManualTrackingAlertDialogue : ExerciseListEvent()
    object HideManualTrackingAlertDialogue : ExerciseListEvent()
    data class ShowExerciseDemo(val exerciseId: Int) : ExerciseListEvent()
    object HideExerciseDemo : ExerciseListEvent()
    data class SaveDataButtonClicked(val testId: String, val exercise: Exercise) :
        ExerciseListEvent()

    data class ManualSelectedExerciseId(val exerciseId: Int) : ExerciseListEvent()
    data class ManualRepetitionCountEntered(val value: String) : ExerciseListEvent()
    data class ManualSetCountEntered(val value: String) : ExerciseListEvent()
    data class ManualWrongCountEntered(val value: String) : ExerciseListEvent()
    data class ExerciseSearchTermEntered(val testId: String, val searchTerm: String) :
        ExerciseListEvent()
}
