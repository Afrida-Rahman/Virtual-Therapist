package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model

data class Answer(
    val bodyLocation: String,
    val id: Int,
    val labels: List<Any>,
    val name: String,
    val referenceId: Int,
    val symptoms: List<Any>,
    val triggers: List<Any>,
    val types: List<Any>
)