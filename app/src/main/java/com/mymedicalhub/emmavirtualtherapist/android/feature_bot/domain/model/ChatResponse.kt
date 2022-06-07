package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model

data class ChatResponse(
    val success: Boolean,
    val message: String,
    val responseData: ResponseData,
    val errors: List<String>
)