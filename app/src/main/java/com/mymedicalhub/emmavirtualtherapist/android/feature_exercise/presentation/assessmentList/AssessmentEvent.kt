package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.assessmentList

sealed class AssessmentEvent {
    object FetchAssessments : AssessmentEvent()
    object ShowAssessmentSearchBar : AssessmentEvent()
    object HideAssessmentSearchBar : AssessmentEvent()
    data class AssessmentSearchTermEntered(val searchTerm: String) : AssessmentEvent()
}
