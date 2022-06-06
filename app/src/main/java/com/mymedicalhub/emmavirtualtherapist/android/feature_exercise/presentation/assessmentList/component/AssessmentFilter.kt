package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.assessmentList.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mymedicalhub.emmavirtualtherapist.android.core.component.OutlineInputTextField
import com.mymedicalhub.emmavirtualtherapist.android.core.component.PrimaryLargeButton
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun AssessmentFilter(
    testIdField: State<String>,
    onTestIdValueChanged: (value: String) -> Unit = {},
    onClickApply: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 12.dp)
            .fillMaxWidth()
    ) {
        OutlineInputTextField(
            field = testIdField,
            placeholder = "Test ID",
            onValueChange = { value ->
                onTestIdValueChanged(value)
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Body Region",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold
        )
        OutlineInputTextField(field = testIdField, placeholder = "Test ID")
        Spacer(modifier = Modifier.height(12.dp))
        PrimaryLargeButton(text = "Apply", onClick = onClickApply)
    }
}

@Preview(showBackground = true)
@Composable
fun AssessmentFilterPreview() {
    EmmaVirtualTherapistTheme {
        AssessmentFilter(
            testIdField = remember { mutableStateOf("") }
        )
    }
}