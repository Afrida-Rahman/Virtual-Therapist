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
    onRepetitionValueChanged: (value: String) -> Unit,
    setField: State<String>,
    onSetValueChanged: (value: String) -> Unit,
    wrongField: State<String>,
    onWrongValueChanged: (value: String) -> Unit,
    onCloseClicked: () -> Unit,
    onSaveDataClick: () -> Unit,
    saveDataButtonClickState: State<Boolean>
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = exerciseName,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .weight(1f)
                )
                IconButton(
                    onClick = { onCloseClicked() },
                    modifier = Modifier
                        .size(50.dp)
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close Icon")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlineInputTextField(
                field = repetitionField,
                onValueChange = { onRepetitionValueChanged(it) },
                placeholder = "Repetition Count",
                keyboardType = KeyboardType.Number
            )
            Spacer(modifier = Modifier.height(4.dp))
            OutlineInputTextField(
                field = setField,
                onValueChange = { onSetValueChanged(it) },
                placeholder = "Set Count",
                keyboardType = KeyboardType.Number
            )
            Spacer(modifier = Modifier.height(4.dp))
            OutlineInputTextField(
                field = wrongField,
                onValueChange = { onWrongValueChanged(it) },
                placeholder = "Wrong Count",
                keyboardType = KeyboardType.Number
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { onSaveDataClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                if (saveDataButtonClickState.value) {
                    CircularProgressIndicator()
                } else {
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
}

@Preview(showBackground = true)
@Composable
fun ManualTrackingFormPreview() {
    EmmaVirtualTherapistTheme {
        val repetitionField = remember { mutableStateOf("") }
        val setField = remember { mutableStateOf("") }
        val wrongField = remember { mutableStateOf("") }
        val saveDataButtonClickState = remember { mutableStateOf(false) }
        ManualTrackingForm(
            exerciseName = "AROM Ankle Dorsiflexion in Sitting",
            repetitionField = repetitionField,
            onRepetitionValueChanged = {},
            setField = setField,
            onSetValueChanged = {},
            wrongField = wrongField,
            onWrongValueChanged = {},
            onCloseClicked = {},
            onSaveDataClick = {},
            saveDataButtonClickState = saveDataButtonClickState
        )
    }
}