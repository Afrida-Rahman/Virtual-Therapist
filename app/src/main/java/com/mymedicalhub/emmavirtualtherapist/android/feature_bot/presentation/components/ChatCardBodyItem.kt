package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun ChatCardBodyItem(icon: Painter, title: String) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, bottom = 12.dp, top = 16.dp)
        ) {
            Image(
                painter = icon,
                contentDescription = title,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    color = Color(0xFF000000),
                    style = MaterialTheme.typography.h2,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatCardBodyItemPreview() {
    EmmaVirtualTherapistTheme {
        ChatCardBodyItem(
            icon = painterResource(R.drawable.mmh_logo),
            title = "EMMA",
        )
    }
}