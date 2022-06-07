package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.mymedicalhub.emmavirtualtherapist.android.core.component.Pill
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Green
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Green150

@Composable
fun PillSection(pills: List<String>) {
    FlowRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        pills.forEach {
            Pill(text = it, textColor = Green, backgroundColor = Green150)
            Spacer(modifier = Modifier.width(6.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PillSectionPreview() {
    EmmaVirtualTherapistTheme {
        PillSection(
            pills = listOf(
                "General",
                "Activities or Hobbies",
                "Activities of Daily Living",
                "This is a long non empty line"
            )
        )
    }
}