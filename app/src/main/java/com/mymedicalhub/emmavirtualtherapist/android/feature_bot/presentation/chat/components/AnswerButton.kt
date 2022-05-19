package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Gray200
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.MediumCharcoal

@Composable
fun AnswerButton(text: String, textColor: Color) {
    Button(
        onClick = { },
        shape = CircleShape,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 1.dp)
            .border(
                width = 1.dp,
                shape = CircleShape,
                color = Gray200
            ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        )
    ) {
        Text(
            text = text,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(4.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnswerButtonPreview() {
    EmmaVirtualTherapistTheme {
        AnswerButton(
            text = "Occasionally",
            textColor = MediumCharcoal,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnswerButtonOnePreview() {
    EmmaVirtualTherapistTheme {
        AnswerButton(
            text = "None",
            textColor = MediumCharcoal,
        )
    }
}
