package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Response
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun MultiselectSection(responses: List<Response>) {
    FlowRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        responses.forEach {
            MultiselectItem(text = it.name, modifier = Modifier.padding(2.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckBoxSectionPreview() {
    EmmaVirtualTherapistTheme {
        MultiselectSection(
            responses = listOf(
                Response(
                    id = 1,
                    name = "Neck",
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
                    id = 2,
                    name = "Arms",
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
                    id = 3,
                    name = "Back",
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
                    id = 4,
                    name = "Legs",
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