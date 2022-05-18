package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.ChatResponse
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Response
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.ResponseData
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun ChatMessage(chatResponse: ChatResponse) {
    val response = chatResponse.responseData
    val tags = listOf(response.header1, response.header2, response.typeName)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        MessageHeader(icon = R.drawable.mmh_logo, title = "EMMA")
        Spacer(modifier = Modifier.height(12.dp))
        PillSection(pills = tags.filter { it.isNotEmpty() })
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = response.dialogue)
        Spacer(modifier = Modifier.height(6.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ChatMessagePreview() {
    EmmaVirtualTherapistTheme {
        ChatMessage(
            ChatResponse(
                success = true,
                message = "Request successful",
                responseData = ResponseData(
                    questionId = 43,
                    text = "How often do you perform activities that require bending or twisting of your torso, such as gardening or golf?",
                    bodyLocation = "",
                    header1 = "General",
                    header2 = "Activities or Hobbies",
                    intent = "",
                    title = "",
                    buttonText = "",
                    dialogue = "How often do you perform activities that require bending or twisting of your torso, such as gardening or golf?",
                    hint = "",
                    exercise = null,
                    typeId = 3,
                    typeName = "Activities of Daily Living",
                    lastQuestionInGroup = false,
                    responseType = "BUTTON",
                    tenant = "stg",
                    pageCaption = "How often do you perform any of the following activities or hobbies?",
                    saveAnswer = true,
                    editable = true,
                    skipped = false,
                    vasQuestion = false,
                    chatEnded = false,
                    audioUrl = "https://mmhva.s3.amazonaws.com/Audio/emma/emma_QUESTION_43_637692152587562092.wav",
                    responses = listOf(
                        Response(
                            id = 89,
                            name = "",
                            hint = "",
                            title = "None",
                            description = "",
                            color = "",
                            icon = "",
                            modalUrl = "",
                            checked = false,
                            referenceId = 0
                        ),
                        Response(
                            id = 90,
                            name = "Monthly",
                            hint = "",
                            title = "",
                            description = "",
                            color = "",
                            icon = "",
                            modalUrl = "",
                            checked = false,
                            referenceId = 0
                        ),
                        Response(
                            id = 91,
                            name = "Weekly",
                            hint = "",
                            title = "",
                            description = "",
                            color = "",
                            icon = "",
                            modalUrl = "",
                            checked = false,
                            referenceId = 0
                        ),
                        Response(
                            id = 92,
                            name = "Daily",
                            hint = "",
                            title = "",
                            description = "",
                            color = "",
                            icon = "",
                            modalUrl = "",
                            checked = false,
                            referenceId = 0
                        )
                    ),
                    disableBodyPartIds = emptyList(),
                    selectedBodyRegions = emptyList(),
                    id = 3,
                    sessionId = "263015c3-c51e-4c31-b8bb-e5833059aba5",
                    botName = "MSK_BOT",
                    bodyParts = emptyList()
                ),
                errors = emptyList()
            )
        )
    }
}

