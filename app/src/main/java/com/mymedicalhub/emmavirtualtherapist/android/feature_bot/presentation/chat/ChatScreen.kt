package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.component.OutlineInputTextField
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Bot
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.ChatResponse
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Response
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.ResponseData
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components.ChatMessage
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun ChatScreen(bot: Bot) {
    val field = remember {
        mutableStateOf("")
    }
    val chatMessages = remember {
        mutableStateOf(
            listOf(
                ChatResponse(
                    success = true,
                    message = "Request successful",
                    responseData = ResponseData(
                        questionId = 43,
                        text = "How often do you perform activities that require bending or twisting of your torso, such as gardening or golf?",
                        bodyLocation = "",
                        header1 = "Activities or Hobbies",
                        header2 = "",
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
                                name = "None",
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
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = bot.name) },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIos,
                            contentDescription = "Go Back"
                        )
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .weight(1f)
            ) {
                items(chatMessages.value) {
                    ChatMessage(chatResponse = it)
                }
            }
            Card(modifier = Modifier.padding(8.dp)) {
                OutlineInputTextField(
                    field = field,
                    onValueChange = {},
                    placeholder = "Your Message",
                    keyboardType = KeyboardType.Text,
                    trailingIcon = R.drawable.ic_send,
                    isEnable = true
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ChatScreenPreview() {
    EmmaVirtualTherapistTheme {
        ChatScreen(
            bot = Bot(
                name = "Pain Recorder Bot",
                codeName = "PAIN_BOT",
                icon = R.drawable.chest_pain
            )
        )
    }
}