package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model

data class Answer(
    val id: Int,
    val name: String,
    val labels: List<String> = emptyList(),
    val bodyLocation: String = "",
    val referenceId: Int = 0,
    val symptoms: List<String> = emptyList(),
    val triggers: List<String> = emptyList(),
    val types: List<String> = emptyList()
)