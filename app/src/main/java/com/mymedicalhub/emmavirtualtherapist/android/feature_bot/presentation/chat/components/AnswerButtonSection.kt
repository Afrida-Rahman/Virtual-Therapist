package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Response
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.ChatEvent
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.ChatViewModel
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.MediumCharcoal

@Composable
fun AnswerButtonSection(questionId: Int, responses: List<Response>, viewModel: ChatViewModel) {
    FlowRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        responses.forEach {
            AnswerButton(
                text = it.name,
                textColor = MediumCharcoal,
                onClick = {
                    viewModel.onEvent(ChatEvent.DisableInput)
                    viewModel.onEvent(
                        ChatEvent.ResponseButtonClicked(
                            questionId = questionId,
                            response = it
                        )
                    )
                },
                modifier = Modifier.padding(4.dp),
                isEnable = viewModel.isInputEnabled.value
            )
        }
    }
}