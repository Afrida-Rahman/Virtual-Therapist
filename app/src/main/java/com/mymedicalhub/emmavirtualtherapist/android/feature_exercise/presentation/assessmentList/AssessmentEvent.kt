package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.assessmentList

sealed class AssessmentEvent {
    object FetchAssessments : AssessmentEvent()
    data class FetchExercises(val testId: String, val tenant: String) : AssessmentEvent()
    data class FetchExerciseConstraints(
        val tenant: String,
        val testId: String,
        val exerciseId: Int
    ) : AssessmentEvent()

    data class ExerciseSearchTermEntered(val testId: String, val searchTerm: String) :
        AssessmentEvent()

    object ShowAssessmentSearchBar : AssessmentEvent()
    object HideAssessmentSearchBar : AssessmentEvent()
    data class AssessmentSearchTermEntered(val searchTerm: String) : AssessmentEvent()
    object SignOut : AssessmentEvent()
}
