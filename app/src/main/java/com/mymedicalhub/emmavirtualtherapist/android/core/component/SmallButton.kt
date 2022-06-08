package com.mymedicalhub.emmavirtualtherapist.android.core.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Blue500
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Blue900
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun SmallButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.White,
    @DrawableRes icon: Int? = null,
    iconColor: Color = MaterialTheme.colors.onPrimary,
    backgroundColor: Color = Blue900,
    isEnable: Boolean = true,
    disabledBackgroundColor: Color = Blue500,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick, colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            disabledBackgroundColor = disabledBackgroundColor
        ),
        shape = CircleShape,
        enabled = isEnable,
        modifier = modifier
            .height(44.dp)
    ) {
        icon?.let {
            Icon(painter = painterResource(id = icon), contentDescription = text, tint = iconColor)
        }
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}

@Preview
@Composable
fun SmallButtonPreview() {
    EmmaVirtualTherapistTheme {
        SmallButton(text = "SAVE")
    }
}