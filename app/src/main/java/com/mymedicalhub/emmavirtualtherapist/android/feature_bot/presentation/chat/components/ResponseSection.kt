package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.component.OutlineInputTextField
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Bot
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.ResponseData
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.ResponseType
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.ChatEvent
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.ChatViewModel
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.utils.Intents
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.utils.Questions
import java.util.*

@Composable
fun ResponseSection(responseData: ResponseData, viewModel: ChatViewModel, bot: Bot) {
    val field = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    val mYear: Int = mCalendar.get(Calendar.YEAR)
    val mMonth: Int = mCalendar.get(Calendar.MONTH)
    val mDay: Int = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

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
            if (responseData.questionId == 10000012 && bot.codeName == Bot.POSTURE.codeName) {
                viewModel.onEvent(
                    ChatEvent.MultiselectQuestionSubmitted(
                        questionId = 10000012,
                        responses = listOf(
                            responseData.responses.find { it.id == 5 }!!
                        )
                    )
                )
            }
        }
        ResponseType.AUTOCOMPLETE -> {}
        ResponseType.DATE -> {}
        ResponseType.DATETIME -> {}
    }
}