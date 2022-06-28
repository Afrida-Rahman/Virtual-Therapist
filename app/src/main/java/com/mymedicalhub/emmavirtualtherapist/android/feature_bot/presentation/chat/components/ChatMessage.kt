package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.component.MediumButton
import com.mymedicalhub.emmavirtualtherapist.android.core.component.OutlineInputTextField
import com.mymedicalhub.emmavirtualtherapist.android.core.component.PrimaryLargeButton
import com.mymedicalhub.emmavirtualtherapist.android.core.component.SmallButton
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Bot
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Response
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.ResponseData
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.ResponseType
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.ChatEvent
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.ChatViewModel
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.utils.Intents
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.utils.Questions
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.*
import java.util.*

@Composable
fun ChatMessage(
    responseData: ResponseData,
    viewModel: ChatViewModel,
    bot: Bot
) {
    val field = remember {
        mutableStateOf("")
    }
    var selectedResponse by remember {
        mutableStateOf<List<Response>>(emptyList())
    }
    var multiSelectedResponse by remember {
        mutableStateOf<List<Response>>(emptyList())
    }
    val imageSize = 600.dp
    var isEditing by remember {
        mutableStateOf(false)
    }
    var showChiefComplaintBody by remember {
        mutableStateOf(false)
    }
    var showFrontSide by remember {
        mutableStateOf(true)
    }
    val context = LocalContext.current

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    val mYear: Int = mCalendar.get(Calendar.YEAR)
    val mMonth: Int = mCalendar.get(Calendar.MONTH)
    val mDay: Int = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val cautionPattern = "Caution!</span>"
    val isCautionText = responseData.dialogue.contains(cautionPattern)
    val tags = listOf(
        responseData.header1,
        responseData.header2,
        responseData.typeName
    )

    if (!isEditing && responseData.answers.isNotEmpty()) {
        selectedResponse = responseData.answers
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        MessageHeader(icon = R.drawable.mmh_logo, title = "EMMA") {
            if (responseData.editable && responseData.answers.isNotEmpty()) {
                if (isEditing) {
                    MediumButton(
                        text = "Cancel",
                        textColor = Color.White,
                        backgroundColor = Color.Black,
                        icon = R.drawable.cross,
                        iconColor = Color.White,
                        onClick = { isEditing = false },
                        modifier = Modifier
                            .size(width = 166.dp, height = 35.dp),
                    )
                } else {
                    MediumButton(
                        text = "Edit",
                        textColor = Color.White,
                        backgroundColor = Green,
                        icon = R.drawable.edit,
                        iconColor = Color.White,
                        onClick = { isEditing = true },
                        modifier = Modifier
                            .size(width = 166.dp, height = 35.dp),
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        PillSection(pills = tags.filter { it.isNotEmpty() })
        Spacer(modifier = Modifier.height(6.dp))
        if (responseData.title.isNotEmpty()) {
            Text(text = responseData.title, fontSize = 24.sp, color = MaterialTheme.colors.primary)
            Spacer(modifier = Modifier.height(12.dp))
        }
        if (isCautionText) {
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Red)) {
                    append("Caution! ")
                }
                append(responseData.dialogue.split(cautionPattern)[1])
            })
        } else {
            Text(text = responseData.dialogue)
        }
        Spacer(modifier = Modifier.height(6.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            if (!isEditing && selectedResponse.isNotEmpty()) {
                selectedResponse.forEach {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        crossAxisAlignment = FlowCrossAxisAlignment.Center,
                        mainAxisAlignment = FlowMainAxisAlignment.End
                    ) {
                        if (it.title.isNotEmpty()) {
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = it.title)
                        }
                        Text(
                            text = it.name,
                            color = Color.White,
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Gray200,
                                    shape = CircleShape
                                )
                                .background(color = Blue900, shape = CircleShape)
                                .padding(vertical = 12.dp, horizontal = 24.dp)
                        )
                    }
                }
            } else {
                when (responseData.responseType) {
                    ResponseType.BUTTON -> {
                        FlowRow(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            responseData.responses.forEach {
                                AnswerButton(
                                    text = it.name,
                                    textColor = MediumCharcoal,
                                    onClick = {
                                        isEditing = false
                                        selectedResponse = listOf(it)
                                        viewModel.onEvent(
                                            ChatEvent.ResponseButtonClicked(
                                                questionId = responseData.questionId,
                                                response = it
                                            )
                                        )
                                    },
                                    modifier = Modifier.padding(4.dp)
                                )
                            }
                        }
                    }
                    ResponseType.TEXT -> {
                        if (responseData.chatEnded) {
                            if (responseData.buttonText.isNotEmpty()) {
                                SmallButton(
                                    text = responseData.buttonText,
                                    backgroundColor = Green,
                                    onClick = { })
                            }
                        } else if (responseData.intent == Intents.AUTO_CLICK.value) {
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
                                    isEditing = false
                                    selectedResponse = listOf(
                                        Response(id = 0, name = field.value)
                                    )
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
                                    isEditing = false
                                    selectedResponse = listOf(
                                        Response(id = 0, name = field.value)
                                    )
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
                                    isEditing = false
                                    selectedResponse = listOf(
                                        Response(id = 0, name = field.value)
                                    )
                                    viewModel.onEvent(
                                        ChatEvent.TextMessageEntered(
                                            questionId = responseData.questionId,
                                            message = "$mDayOfMonth/${month + 1}/$year"
                                        )
                                    )
                                }, mYear, mMonth, mDay
                            )
                            SmallButton(
                                text = "Pick A Date",
                                onClick = { mDatePickerDialog.show() })
                        } else {
                            OutlineInputTextField(
                                field = field,
                                onValueChange = { field.value = it },
                                placeholder = "Your Message",
                                keyboardType = KeyboardType.Text,
                                trailingIcon = R.drawable.ic_send,
                                onIconPressed = {
                                    isEditing = false
                                    selectedResponse = listOf(
                                        Response(id = 0, name = field.value)
                                    )
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
                        FlowRow(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            responseData.responses.forEach { response ->
                                MultiselectItem(
                                    text = response.name,
                                    modifier = Modifier.padding(2.dp),
                                    onClick = {
                                        multiSelectedResponse =
                                            if (multiSelectedResponse.find { it.id == response.id } == null) {
                                                multiSelectedResponse + response
                                            } else {
                                                multiSelectedResponse - response
                                            }
                                    }
                                )
                            }
                        }
                        SmallButton(text = "Ok", onClick = {
                            isEditing = false
                            selectedResponse = multiSelectedResponse
                            viewModel.onEvent(
                                ChatEvent.MultiselectQuestionSubmitted(
                                    responseData.questionId,
                                    selectedResponse
                                )
                            )
                        })
                        if (responseData.questionId == 10000012 && bot.codeName == Bot.POSTURE.codeName) {
                            selectedResponse = listOf(
                                responseData.responses.find { it.id == 5 }!!
                            )
                            viewModel.onEvent(
                                ChatEvent.MultiselectQuestionSubmitted(
                                    questionId = 10000012,
                                    responses = selectedResponse
                                )
                            )
                        }
                    }
                    ResponseType.AUTOCOMPLETE -> {}
                    ResponseType.DATE -> {}
                    ResponseType.DATETIME -> {}
                    ResponseType.MODAL -> {
                        Log.d("ModalQuestion", "Question ID: ${responseData.questionId}")
                        Column(modifier = Modifier.fillMaxWidth()) {
                            AnimatedVisibility(visible = showChiefComplaintBody) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(imageSize)
                                        .padding(vertical = 12.dp)
                                ) {
                                    Image(
                                        painter = painterResource(
                                            id = if (showFrontSide) {
                                                R.drawable.front_image
                                            } else {
                                                R.drawable.back_image
                                            }
                                        ),
                                        contentDescription = "Skeleton",
                                        modifier = Modifier.size(imageSize)
                                    )
                                    Image(
                                        painter = painterResource(
                                            id = if (showFrontSide) {
                                                R.drawable.ic_front_points
                                            } else {
                                                R.drawable.ic_back_points
                                            }
                                        ),
                                        contentDescription = "Front key points",
                                        modifier = Modifier.size(imageSize)
                                    )
                                    SmallButton(
                                        text = "Flip",
                                        onClick = { showFrontSide = !showFrontSide },
                                        modifier = Modifier.align(
                                            Alignment.TopEnd
                                        )
                                    )
                                }
                            }
                            if (showChiefComplaintBody) {
                                PrimaryLargeButton(
                                    text = "Save and Close",
                                    onClick = {
                                        viewModel.onEvent(
                                            ChatEvent.ResponseButtonClicked(
                                                questionId = responseData.questionId,
                                                response = responseData.responses[0]
                                            )
                                        )
                                    }
                                )
                            } else {
                                Spacer(modifier = Modifier.height(12.dp))
                                PrimaryLargeButton(
                                    text = responseData.responses[0].name,
                                    onClick = {
                                        showChiefComplaintBody = true
                                    }
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                PrimaryLargeButton(
                                    text = responseData.responses[1].name,
                                    onClick = {
                                        viewModel.onEvent(
                                            ChatEvent.ResponseButtonClicked(
                                                questionId = responseData.questionId,
                                                response = responseData.responses[1]
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}