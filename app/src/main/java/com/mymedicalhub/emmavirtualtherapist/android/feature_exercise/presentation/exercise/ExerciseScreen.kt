package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exercise

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun ExerciseScreen() {
    Column(modifier = Modifier.fillMaxSize()) { }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExerciseScreenPreview() {
    EmmaVirtualTherapistTheme {
        ExerciseScreen()
    }
}