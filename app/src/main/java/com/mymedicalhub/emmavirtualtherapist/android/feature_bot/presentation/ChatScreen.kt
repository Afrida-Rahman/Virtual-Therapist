package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation

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
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Message
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.components.ChatMessage
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun ChatScreen(bot: Bot) {
    val field = remember {
        mutableStateOf("")
    }
    val chatMessages = remember {
        mutableStateOf<List<Message>>(
            listOf(
                Message(
                    message = "Very Goof Morning...! We are growing gradually. Here is the complete growth report.",
                    userIcon = R.drawable.chest_pain
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
                    ChatMessage(message = it)
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