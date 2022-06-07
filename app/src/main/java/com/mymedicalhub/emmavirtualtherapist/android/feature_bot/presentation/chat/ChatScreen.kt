package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.UIEvent
import com.mymedicalhub.emmavirtualtherapist.android.core.component.CustomTopAppBar
import com.mymedicalhub.emmavirtualtherapist.android.core.component.Pill
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components.ChatMessage
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components.Loading
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components.ResponseSection
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components.ResponseSubmittedDisplaySection
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.utils.BotUtils
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Yellow
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(
    navController: NavController,
    codeName: String,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val chatResponses = viewModel.chatResponses
    val bot = BotUtils.getBot(codeName)
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val listState = rememberLazyListState()
    val lastItemIndex = chatResponses.value.size - 1

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(ChatEvent.InitializeBotInformation(botName = bot.codeName))
        viewModel.onEvent(ChatEvent.TextMessageEntered(questionId = 0, message = "Hi"))
        viewModel.eventFlow.collect { event ->
            when (event) {
                is UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                is UIEvent.ShowToastMessage -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    LaunchedEffect(lastItemIndex) {
        if (lastItemIndex > 0) {
            coroutineScope.launch {
                listState.animateScrollToItem(lastItemIndex)
            }
        } else if (viewModel.isLoading.value) {
            if (lastItemIndex > 0) {
                coroutineScope.launch {
                    listState.animateScrollToItem(lastItemIndex + 1)
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CustomTopAppBar(
                leadingIcon = R.drawable.ic_arrow_back,
                onClickLeadingIcon = { navController.popBackStack() }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = bot.name, style = MaterialTheme.typography.h3)
                    Row {
                        if (viewModel.bodyRegions.value.isNotEmpty()) {
                            Pill(
                                text = "Body Region: ${viewModel.bodyRegions.value}",
                                textColor = Color.Black,
                                backgroundColor = Yellow
                            )
                        }
                        if (viewModel.testId.value.isNotEmpty()) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Pill(
                                text = "Test ID: ${viewModel.testId.value}",
                                textColor = Color.Black,
                                backgroundColor = Yellow
                            )
                        }
                    }
                }
            }
        },
        backgroundColor = MaterialTheme.colors.surface
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState
        ) {
            items(chatResponses.value) { chatResponse ->
                val responseData = chatResponse.responseData
                Spacer(modifier = Modifier.height(12.dp))
                ChatMessage(
                    questionText = responseData.dialogue,
                    tags = listOf(
                        responseData.header1,
                        responseData.header2,
                        responseData.typeName
                    )
                ) {
                    if (responseData.answers.isNotEmpty()) {
                        ResponseSubmittedDisplaySection(
                            responseData = responseData, viewModel = viewModel, bot = bot
                        )
                    } else {
                        ResponseSection(
                            responseData = responseData, viewModel = viewModel, bot = bot
                        )
                    }
                }
            }
            item {
                if (viewModel.isLoading.value) {
                    Loading()
                }
            }
        }
    }
}