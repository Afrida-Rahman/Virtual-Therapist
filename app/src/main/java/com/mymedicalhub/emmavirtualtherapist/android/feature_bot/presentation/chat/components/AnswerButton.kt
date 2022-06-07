package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.MediumCharcoal

@Composable
fun AnswerButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    isEnable: Boolean = true,
    onClick: () -> Unit = {}
) {
    OutlinedButton(
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.White
        ),
        modifier = modifier,
        enabled = isEnable
    ) {
        Text(
            text = text,
            color = textColor,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnswerButtonPreview() {
    EmmaVirtualTherapistTheme {
        AnswerButton(
            text = "None",
            textColor = MediumCharcoal,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnswerButtonOnePreview() {
    EmmaVirtualTherapistTheme {
        AnswerButton(
            text = "Occasionally",
            textColor = MediumCharcoal,
        )
    }
}