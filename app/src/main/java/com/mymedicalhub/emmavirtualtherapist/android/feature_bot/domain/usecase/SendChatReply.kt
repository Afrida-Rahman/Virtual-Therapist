package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.usecase

import com.mymedicalhub.emmavirtualtherapist.android.core.Resource
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.data.dto.toChatResponse
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.ChatPayload
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.ChatResponse
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.repository.ChatApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SendChatReply @Inject constructor(
    private val chatApi: ChatApi
) {
    operator fun invoke(
        chatPayload: ChatPayload
    ): Flow<Resource<ChatResponse>> = flow {
        emit(Resource.Loading())
        try {
            val chatResponseDto = when (chatPayload.botName) {
                "MSK_BOT" -> chatApi.sendMSKChatReply(payload = chatPayload)
                "POSTURE_BOT" -> chatApi.sendPostureChatReply(payload = chatPayload)
                "PAIN" -> chatApi.sendAppointmentChatReply(payload = chatPayload)
                else -> chatApi.sendMSKChatReply(payload = chatPayload)
            }
            emit(
                Resource.Success(
                    chatResponseDto.toChatResponse()
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error("Could not reach to the server"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred!"))
        }
    }
}