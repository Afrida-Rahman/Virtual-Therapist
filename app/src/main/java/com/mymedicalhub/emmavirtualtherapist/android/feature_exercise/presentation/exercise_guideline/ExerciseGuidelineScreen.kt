package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exercise_guideline

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exercise_guideline.component.ImageSection
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exercise_guideline.component.InstructionSection
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exercise_guideline.component.VideoSection
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun ExerciseGuidelineScreen() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.scrollable(scrollState, orientation = Orientation.Horizontal)
    ) {
        InstructionSection()
        VideoSection(videoUrl = "null")
        ImageSection()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExerciseGuidelineScreenPreview() {
    EmmaVirtualTherapistTheme {
        ExerciseGuidelineScreen()
    }
}