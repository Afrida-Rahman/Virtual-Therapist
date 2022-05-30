package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.repository

import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.data.dto.ChatResponseDto
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.ChatPayload
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatApi {

    @POST("/chat/msk")
    suspend fun sendMSKChatReply(@Body payload: ChatPayload): ChatResponseDto

    @POST("/chat/posture")
    suspend fun sendPostureChatReply(@Body payload: ChatPayload): ChatResponseDto

    @POST("/chat/appointment")
    suspend fun sendAppointmentChatReply(@Body payload: ChatPayload): ChatResponseDto
}