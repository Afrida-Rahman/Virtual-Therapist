package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun CheckBoxSection(bodyLocationList: List<String>) {
    FlowRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        bodyLocationList.forEach {
            CheckBox(text = it)
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckBoxSectionPreview() {
    EmmaVirtualTherapistTheme {
        CheckBoxSection(
            bodyLocationList = listOf(
                "Neck",
                "Arms",
                "Back",
                "Legs"
            )
        )

    }
}