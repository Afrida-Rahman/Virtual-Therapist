package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.component.MediumButton
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Green

@Composable
fun MessageHeader(
    @DrawableRes icon: Int,
    title: String,
    trailingContent: @Composable () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = title,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = title,
                fontSize = 14.sp,
                color = Color.Black,
                style = MaterialTheme.typography.h2,
            )
        }
        trailingContent()
    }
}

@Preview(showBackground = true)
@Composable
fun MessageHeaderPreview() {
    EmmaVirtualTherapistTheme {
        MessageHeader(icon = R.drawable.mmh_logo, title = "EMMA") {
            MediumButton(
                text = "Edit",
                textColor = Color.White,
                backgroundColor = Green,
                icon = R.drawable.edit,
                iconColor = Color.White,
                onClick = { },
                modifier = Modifier
                    .size(width = 166.dp, height = 35.dp),
            )
        }
    }
}