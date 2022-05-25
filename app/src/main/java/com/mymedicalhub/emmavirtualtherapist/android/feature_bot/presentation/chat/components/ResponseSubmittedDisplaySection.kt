package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Response
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun ResponseSubmittedDisplaySection(responses: List<Response>) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
        if (responses.size == 1) {
            Text(text = responses[0].name)
        } else if (responses.size > 1) {
            Column {
                responses.forEach {
                    Text(text = it.name)
                }
            }
        }
    }
}

@Preview
@Composable
fun ResponseSubmittedDisplaySectionPreviewWithSingleResponse() {
    EmmaVirtualTherapistTheme {
        ResponseSubmittedDisplaySection(
            responses = listOf(
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
                )
            )
        )
    }
}

@Preview
@Composable
fun ResponseSubmittedDisplaySectionPreviewWithMultipleResponse() {
    EmmaVirtualTherapistTheme {
        ResponseSubmittedDisplaySection(
            responses = listOf(
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
            )
        )
    }
}