package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Message
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun ChatMessage(message: Message) {
    val roundingAmount = 50.dp
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Image(
            painter = painterResource(id = message.userIcon),
            contentDescription = "User Icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(width = 2.dp, color = Color.Blue.copy(alpha = 0.3f), shape = CircleShape)
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Card(
            modifier = Modifier
                .background(Color.White)
                .clip(
                    RoundedCornerShape(
                        topStart = roundingAmount,
                        topEnd = roundingAmount,
                        bottomEnd = roundingAmount
                    )
                ),
            elevation = 12.dp
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(12.dp)
            ) {
                Text(text = message.message)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = message.messageTime,
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChatMessagePreview() {
    EmmaVirtualTherapistTheme {
        ChatMessage(
            message = Message(
                message = "Very Goof Morning...! We are growing gradually. Here is the complete growth report.",
                userIcon = R.drawable.chest_pain
            )
        )
    }
}