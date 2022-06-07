package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mymedicalhub.emmavirtualtherapist.android.R

@Composable
fun ChatMessage(questionText: String, tags: List<String>, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        MessageHeader(icon = R.drawable.mmh_logo, title = "EMMA")
        Spacer(modifier = Modifier.height(12.dp))
        PillSection(pills = tags.filter { it.isNotEmpty() })
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = questionText)
        Spacer(modifier = Modifier.height(6.dp))
        content()
    }
}