package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exercise_guideline.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun VideoSection(videoUrl: String?) {
    videoUrl?.let {
        if (it.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Exercise Video", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VideoSectionPreview() {
    EmmaVirtualTherapistTheme {
        VideoSection(videoUrl = "null")
    }
}