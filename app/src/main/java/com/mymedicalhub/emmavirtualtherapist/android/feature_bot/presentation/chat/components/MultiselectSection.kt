package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Response
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.ChatEvent
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.ChatViewModel

@Composable
fun MultiselectSection(
    questionId: Int,
    responses: List<Response>,
    viewModel: ChatViewModel
) {
    val selectedResponse = remember {
        mutableStateOf<List<Response>>(emptyList())
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        FlowRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            responses.forEach { response ->
                MultiselectItem(
                    text = response.name,
                    modifier = Modifier.padding(2.dp),
                    onClick = {
                        if (selectedResponse.value.find { it.id == response.id } == null) {
                            selectedResponse.value = selectedResponse.value + response
                        } else {
                            selectedResponse.value = selectedResponse.value - response
                        }
                    }
                )
            }
        }
        Button(onClick = {
            viewModel.onEvent(
                ChatEvent.MultiselectQuestionSubmitted(
                    questionId,
                    selectedResponse.value
                )
            )
        }, modifier = Modifier.padding(start = 16.dp)) {
            Text(text = "Ok")
        }
    }
}