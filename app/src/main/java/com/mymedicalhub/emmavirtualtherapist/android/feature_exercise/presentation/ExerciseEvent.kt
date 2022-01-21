package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation

import androidx.compose.runtime.State

sealed class ExerciseEvent {
    object FetchAssessments : ExerciseEvent()
    object ShowSearchBar : ExerciseEvent()
    object HideSearchBar : ExerciseEvent()
    object FlipCamera : ExerciseEvent()
    object GoToAssessmentPage : ExerciseEvent()
    data class ShowManualTrackingAlertDialogue(
        val exerciseName: String,
        val repetitionField: State<String>,
        val onRepetitionValueChanged: (value: Int) -> Unit,
        val setField: State<String>,
        val onSetValueChanged: (value: Int) -> Unit,
        val wrongField: State<String>,
        val onWrongValueChanged: (value: Int) -> Unit,
        val onCloseClicked: () -> Unit,
        val onSaveDataClick: () -> Unit
    ) : ExerciseEvent()

    data class SearchTermEntered(val testId: String, val searchTerm: String) : ExerciseEvent()
}