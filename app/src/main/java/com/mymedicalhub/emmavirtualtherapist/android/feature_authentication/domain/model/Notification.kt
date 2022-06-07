package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model

data class Notification(
    val type: NotificationType,
    val title: String,
    val description: String,
    val date: String? = null,
    val tags: List<String> = emptyList()
)