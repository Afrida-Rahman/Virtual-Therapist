package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.component.MediumButton
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Bot
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.ResponseData
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.ResponseType
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.ChatViewModel
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Gray200
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Green

@Composable
fun ResponseSubmittedDisplaySection(
    responseData: ResponseData,
    viewModel: ChatViewModel,
    bot: Bot
) {
    var isEditing by remember {
        mutableStateOf(false)
    }
    val responseType = responseData.responseType

    if (isEditing) {
        Column {
            ResponseSection(responseData = responseData, viewModel = viewModel, bot = bot)
            MediumButton(
                text = "Cancel",
                textColor = Color.White,
                backgroundColor = Color.Black,
                icon = R.drawable.ic_cross,
                iconColor = Color.White,
                onClick = {
                    isEditing = !isEditing
                }
            )
        }
    } else {
        Column(modifier = Modifier.fillMaxWidth()) {
            when (responseType) {
                ResponseType.BUTTON -> {
                    val answer = responseData.answers[0]
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        crossAxisAlignment = FlowCrossAxisAlignment.Center
                    ) {
                        Text(
                            text = answer.name,
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Gray200,
                                    shape = CircleShape
                                )
                                .padding(vertical = 12.dp, horizontal = 24.dp)
                        )
                        if (answer.title.isNotEmpty()) {
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = answer.title)
                        }
                    }
                }
                else -> {
                    Column {
                        responseData.answers.forEach {
                            Text(text = it.name)
                        }
                    }
                }
            }
            if (responseData.editable) {
                Spacer(modifier = Modifier.height(6.dp))
                Button(
                    onClick = {
                        isEditing = true
                    },
                    modifier = Modifier
                        .size(width = 166.dp, height = 35.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Green
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "Edit", color = Color.White)
                }
            }
        }
    }
}