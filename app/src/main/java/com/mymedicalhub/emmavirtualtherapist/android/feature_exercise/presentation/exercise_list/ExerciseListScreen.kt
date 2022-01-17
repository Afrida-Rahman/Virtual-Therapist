package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exercise_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exercise_list.component.ExerciseCard
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun ExerciseListScreen() {
    val exerciseList = listOf(
        1, 2, 3, 4, 5, 6, 7
    )
    Column {
        Row(
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Text(text = "Home Exercises", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }
        if (exerciseList.isNotEmpty()) {
            LazyColumn {
                items(exerciseList) {
                    ExerciseCard(
                        imageUrl = null,
                        name = "Body Weight Squat",
                        repetition = 5,
                        set = 3,
                        isActive = true
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExerciseListScreenPreview() {
    EmmaVirtualTherapistTheme {
        ExerciseListScreen()
    }
}