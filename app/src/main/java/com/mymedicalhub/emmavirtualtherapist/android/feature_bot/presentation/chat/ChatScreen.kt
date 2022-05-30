package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.UIEvent
import com.mymedicalhub.emmavirtualtherapist.android.core.component.CustomTopAppBar
import com.mymedicalhub.emmavirtualtherapist.android.core.component.OutlineInputTextField
import com.mymedicalhub.emmavirtualtherapist.android.core.component.Pill
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.ResponseType
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components.*
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.utils.BotUtils
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.utils.Intents
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.utils.Questions
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Yellow
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun ChatScreen(
    navController: NavController,
    codeName: String,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val field = remember {
        mutableStateOf("")
    }
    val chatResponses = viewModel.chatResponses
    val bot = BotUtils.getBot(codeName)
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val listState = rememberLazyListState()
    val lastItemIndex = chatResponses.value.size - 1

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    val mYear: Int = mCalendar.get(Calendar.YEAR)
    val mMonth: Int = mCalendar.get(Calendar.MONTH)
    val mDay: Int = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

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
                    Text(text = bot.name)
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
                            responses = responseData.answers,
                            responseType = responseData.responseType,
                            isEditable = responseData.editable,
                            onClickEdit = if (responseData.editable) {
                                { }
                            } else {
                                { }
                            }
                        )
                    } else {
                        when (responseData.responseType) {
                            ResponseType.BUTTON -> {
                                AnswerButtonSection(
                                    questionId = responseData.questionId,
                                    responses = responseData.responses,
                                    viewModel = viewModel
                                )
                            }
                            ResponseType.TEXT -> {
                                if (responseData.intent == Intents.AUTO_CLICK.value) {
                                    viewModel.onEvent(
                                        ChatEvent.TextMessageEntered(
                                            questionId = responseData.questionId,
                                            message = viewModel.bodyRegions.value
                                        )
                                    )
                                } else if (responseData.questionId == Questions.HEIGHT.id) {
                                    OutlineInputTextField(
                                        field = field,
                                        onValueChange = { field.value = it },
                                        placeholder = "Your Height (Inch)",
                                        keyboardType = KeyboardType.Number,
                                        trailingIcon = R.drawable.ic_send,
                                        onIconPressed = {
                                            viewModel.onEvent(
                                                ChatEvent.TextMessageEntered(
                                                    questionId = responseData.questionId,
                                                    message = field.value
                                                )
                                            )
                                            field.value = ""
                                        },
                                        imeAction = ImeAction.Go
                                    )
                                } else if (responseData.questionId == Questions.WEIGHT.id) {
                                    OutlineInputTextField(
                                        field = field,
                                        onValueChange = { field.value = it },
                                        placeholder = "Your Weight (LBS)",
                                        keyboardType = KeyboardType.Number,
                                        trailingIcon = R.drawable.ic_send,
                                        onIconPressed = {
                                            viewModel.onEvent(
                                                ChatEvent.TextMessageEntered(
                                                    questionId = responseData.questionId,
                                                    message = field.value
                                                )
                                            )
                                            field.value = ""
                                        },
                                        imeAction = ImeAction.Go
                                    )
                                } else if (responseData.questionId == Questions.DOB.id) {
                                    val mDatePickerDialog = DatePickerDialog(
                                        context,
                                        { _: DatePicker, year: Int, month: Int, mDayOfMonth: Int ->
                                            viewModel.onEvent(
                                                ChatEvent.TextMessageEntered(
                                                    questionId = responseData.questionId,
                                                    message = "$mDayOfMonth/${month + 1}/$year"
                                                )
                                            )
                                        }, mYear, mMonth, mDay
                                    )
                                    Button(onClick = { mDatePickerDialog.show() }) {
                                        Text(text = "Pick A Date")
                                    }
                                } else {
                                    OutlineInputTextField(
                                        field = field,
                                        onValueChange = { field.value = it },
                                        placeholder = "Your Message",
                                        keyboardType = KeyboardType.Text,
                                        trailingIcon = R.drawable.ic_send,
                                        onIconPressed = {
                                            viewModel.onEvent(
                                                ChatEvent.TextMessageEntered(
                                                    questionId = responseData.questionId,
                                                    message = field.value
                                                )
                                            )
                                            field.value = ""
                                        },
                                        imeAction = ImeAction.Go
                                    )
                                }
                            }
                            ResponseType.CHECKBOX -> {
                                MultiselectSection(
                                    questionId = responseData.questionId,
                                    responses = responseData.responses,
                                    viewModel = viewModel
                                )
                            }
                            ResponseType.AUTOCOMPLETE -> {}
                            ResponseType.DATE -> {}
                            ResponseType.DATETIME -> {}
                        }
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