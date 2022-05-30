package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model

data class ChatPayload(
    val botName: String,
    val tenant: String,
    val email: String,
    val questionId: Int = 0,
    val answers: List<Answer> = emptyList(),
    val demographicsCompleted: Boolean = false,
    val employerEnabled: Boolean = false,
    val gender: String = "Male",
    val id: Int = 0,
    val intent: String = "",
    val isFollowUp: Boolean = false,
    val lang: String = "en",
    val loggedTime: String = "",
    val patientId: String = "",
    val providerId: String = "",
    val referEnabled: Boolean = false,
    val referenceId: Int = 0,
    val selectedBodyParts: List<String> = emptyList(),
    val sessionId: String = "",
    val skipped: Boolean = false,
    val testId: String = ""
)