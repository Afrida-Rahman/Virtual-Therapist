package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model

data class ChatPayload(
    val answers: List<Answer>,
    val botName: String,
    val demographicsCompleted: Boolean,
    val email: String,
    val employerEnabled: Boolean,
    val gender: String,
    val id: Int,
    val intent: String,
    val isFollowUp: Boolean,
    val lang: String,
    val loggedTime: String,
    val patientId: String,
    val providerId: String,
    val questionId: Int,
    val referEnabled: Boolean,
    val referenceId: String,
    val selectedBodyParts: List<Any>,
    val sessionId: String,
    val skipped: Boolean,
    val tenant: String,
    val testId: String
)