package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Response
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.MediumCharcoal

@Composable
fun AnswerButtonSection(responses: List<Response>) {
    FlowRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        responses.forEach {
            AnswerButton(text = it.name, textColor = MediumCharcoal)
            Spacer(modifier = Modifier.width(4.dp))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun AnswerButtonSectionPreview() {
    EmmaVirtualTherapistTheme {
        AnswerButtonSection(
            responses = listOf(
                Response(
                    id = 89,
                    name = "None",
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
                Response(
                    id = 92,
                    name = "Daily",
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
                    id = 92,
                    name = "Occasionally",
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