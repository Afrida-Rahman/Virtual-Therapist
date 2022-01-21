package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mymedicalhub.emmavirtualtherapist.android.core.component.OutlineInputTextField
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun ManualTrackingForm(
    exerciseName: String,
    repetitionField: State<String>,
    onRepetitionValueChanged: (value: Int) -> Unit,
    setField: State<String>,
    onSetValueChanged: (value: Int) -> Unit,
    wrongField: State<String>,
    onWrongValueChanged: (value: Int) -> Unit,
    onCloseClicked: () -> Unit,
    onSaveDataClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = exerciseName,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
                IconButton(onClick = { onCloseClicked() }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close Icon")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlineInputTextField(
                field = repetitionField,
                onValueChange = { onRepetitionValueChanged(it.toInt()) },
                placeholder = "Repetition Count",
                keyboardType = KeyboardType.Number
            )
            Spacer(modifier = Modifier.height(4.dp))
            OutlineInputTextField(
                field = setField,
                onValueChange = { onSetValueChanged(it.toInt()) },
                placeholder = "Set Count",
                keyboardType = KeyboardType.Number
            )
            Spacer(modifier = Modifier.height(4.dp))
            OutlineInputTextField(
                field = wrongField,
                onValueChange = { onWrongValueChanged(it.toInt()) },
                placeholder = "Wrong Count",
                keyboardType = KeyboardType.Number
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { onSaveDataClick() }) {
                Text(
                    text = "Save Data",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ManualTrackingFormPreview() {
    EmmaVirtualTherapistTheme {
        val repetitionField = remember { mutableStateOf("") }
        val setField = remember { mutableStateOf("") }
        val wrongField = remember { mutableStateOf("") }
        ManualTrackingForm(
            exerciseName = "Bird Dog",
            repetitionField = repetitionField,
            onRepetitionValueChanged = {},
            setField = setField,
            onSetValueChanged = {},
            wrongField = wrongField,
            onWrongValueChanged = {},
            onCloseClicked = {},
            onSaveDataClick = {}
        )
    }
}