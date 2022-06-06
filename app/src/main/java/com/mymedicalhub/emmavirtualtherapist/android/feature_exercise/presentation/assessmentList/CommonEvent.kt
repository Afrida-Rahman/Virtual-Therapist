package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.assessmentList

sealed class CommonEvent {
    object FetchAssessments : CommonEvent()
    data class FetchExercises(val testId: String, val tenant: String) : CommonEvent()
    data class FetchExerciseConstraints(
        val tenant: String,
        val testId: String,
        val exerciseId: Int
    ) : CommonEvent()

    object ShowAssessmentFilter : CommonEvent()
    object HideAssessmentFilter : CommonEvent()
    object ApplyAssessmentFilter : CommonEvent()
    data class ApplyExerciseFilter(val testId: String, val searchTerm: String) : CommonEvent()
    data class AssessmentSearchTermEntered(val searchTerm: String) : CommonEvent()
    object SignOut : CommonEvent()
}
