package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Response
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun AnswerButtonSection(responses: List<Response>) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

    }

}


@Preview(showBackground = true)
@Composable
fun AnswerButtonSectionPreview() {
    EmmaVirtualTherapistTheme {
        AnswerButtonSection(
            responses = listOf(
              
            )
        )
    }
}