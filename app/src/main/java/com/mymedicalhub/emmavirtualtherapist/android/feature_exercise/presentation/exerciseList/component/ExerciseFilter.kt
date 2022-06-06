package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exerciseList.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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
    field: State<String>,
    onValueChange: (value: String) -> Unit = {},
    onClickApply: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 12.dp)
            .fillMaxWidth()
    ) {
        OutlineInputTextField(
            field = field,
            placeholder = "Exercise Name",
            onValueChange = { value ->
                onValueChange(value)
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        PrimaryLargeButton(text = "Apply", onClick = onClickApply)
    }
}

@Preview
@Composable
fun ExerciseFilterPreview() {
    EmmaVirtualTherapistTheme {
        val field = remember {
            mutableStateOf("")
        }
        ExerciseFilter(field)
    }
}