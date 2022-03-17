package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.domain.model.Bot
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun BotCard(bot: Bot, onClick: () -> Unit = {}) {
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .padding(8.dp)
            .size(150.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = bot.icon),
                contentDescription = bot.name,
                modifier = Modifier.size(70.dp)
            )
            Text(text = bot.name, style = MaterialTheme.typography.h6, textAlign = TextAlign.Center)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BotCardPreview() {
    EmmaVirtualTherapistTheme {
        BotCard(
            bot = Bot(
                "Pain Recorder Bot",
                codeName = "PAIN",
                icon = R.drawable.chest_pain
            )
        )
    }
}