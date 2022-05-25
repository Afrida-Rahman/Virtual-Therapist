package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.data.dto

import com.google.gson.annotations.SerializedName
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.ChatResponse


data class ChatResponseDto(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val remoteResponseData: RemoteResponseData,
    @SerializedName("errors") val errors: List<String>
)

fun ChatResponseDto.toChatResponse(): ChatResponse {
    return ChatResponse(
        success = success,
        message = message,
        responseData = remoteResponseData.toResponseData(),
        errors = errors
    )
}
