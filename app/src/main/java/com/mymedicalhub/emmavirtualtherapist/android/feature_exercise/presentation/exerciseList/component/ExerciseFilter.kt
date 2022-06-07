package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exerciseList.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mymedicalhub.emmavirtualtherapist.android.core.component.OutlineInputTextField
import com.mymedicalhub.emmavirtualtherapist.android.core.component.PrimaryLargeButton
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun ExerciseFilter(
    onClickApply: (exerciseName: String) -> Unit = {}
) {
    val field = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 12.dp)
            .fillMaxWidth()
    ) {
        OutlineInputTextField(
            field = field,
            placeholder = "Exercise Name",
            onValueChange = { field.value = it }
        )
        Spacer(modifier = Modifier.height(12.dp))
        PrimaryLargeButton(text = "Apply", onClick = { onClickApply(field.value) })
    }
}

@Preview
@Composable
fun ExerciseFilterPreview() {
    EmmaVirtualTherapistTheme {
        ExerciseFilter()
    }
}