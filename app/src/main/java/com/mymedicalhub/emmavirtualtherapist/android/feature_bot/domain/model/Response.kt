package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model

data class Response(
    val checked: Boolean,
    val color: String,
    val description: String,
    val hint: String,
    val icon: String,
    val id: Int,
    val modalUrl: String,
    val name: String,
    val referenceId: Int,
    val title: String
)