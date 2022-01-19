package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exercise_guideline.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InstructionSection(instruction: String) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text(text = "Exercise Instruction", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = instruction)
    }
}