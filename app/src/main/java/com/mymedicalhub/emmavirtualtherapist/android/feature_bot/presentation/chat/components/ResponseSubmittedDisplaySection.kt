package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Response
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.ResponseType
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Gray200
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Green

@Composable
fun ResponseSubmittedDisplaySection(
    responses: List<Response>,
    responseType: ResponseType = ResponseType.BUTTON,
    isEditable: Boolean = true,
    onClickEdit: () -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        when (responseType) {
            ResponseType.BUTTON -> {
                val response = responses[0]
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    crossAxisAlignment = FlowCrossAxisAlignment.Center
                ) {
                    Text(
                        text = response.name,
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Gray200,
                                shape = CircleShape
                            )
                            .padding(vertical = 12.dp, horizontal = 24.dp)
                    )
                    if (response.title.isNotEmpty()) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = response.title)
                    }
                }
            }
            else -> {
                Column {
                    responses.forEach {
                        Text(text = it.name)
                    }
                }
            }
        }
        if (isEditable) {
            Spacer(modifier = Modifier.height(6.dp))
            Button(
                onClick = onClickEdit,
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
                    title = "This is a text",
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