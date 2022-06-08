package com.mymedicalhub.emmavirtualtherapist.android.core.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun MediumButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    backgroundColor: Color,
    @DrawableRes icon: Int? = null,
    iconColor: Color = MaterialTheme.colors.onPrimary,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier.size(height = 40.dp, width = 166.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        )
    ) {
        icon?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = text,
                tint = iconColor
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = text,
            color = textColor,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MediumButtonPreview() {
    EmmaVirtualTherapistTheme {
        MediumButton(
            text = "Home Exercise",
            textColor = Color.White,
            backgroundColor = Color.Red,
            icon = R.drawable.run,
            iconColor = Color.White
        )
    }
}