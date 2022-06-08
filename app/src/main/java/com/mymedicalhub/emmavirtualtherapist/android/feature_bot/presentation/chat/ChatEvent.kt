package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat

import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Response

sealed class ChatEvent {
    data class ResponseButtonClicked(
        val questionId: Int, val response: Response
    ) : ChatEvent()

    data class MultiselectQuestionSubmitted(
        val questionId: Int, val responses: List<Response>
    ) : ChatEvent()

    data class InitializeBotInformation(val botCodeName: String) : ChatEvent()
    data class TextMessageEntered(val questionId: Int, val message: String) : ChatEvent()
}
