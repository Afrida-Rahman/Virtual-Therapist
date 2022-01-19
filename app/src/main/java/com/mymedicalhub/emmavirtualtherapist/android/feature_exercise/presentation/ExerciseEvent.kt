package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation

sealed class ExerciseEvent {
    object FetchAssessments : ExerciseEvent()
    object ShowSearchBar : ExerciseEvent()
    object HideSearchBar : ExerciseEvent()
    data class SearchTermEntered(val testId: String, val searchTerm: String) : ExerciseEvent()
}