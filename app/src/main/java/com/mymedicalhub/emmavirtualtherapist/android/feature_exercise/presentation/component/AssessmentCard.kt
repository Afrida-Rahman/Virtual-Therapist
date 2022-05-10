package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Assessment
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun AssessmentCard(assessment: Assessment, onViewExerciseButtonClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .shadow(4.dp, RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            AssessmentCardBody(
                testId = assessment.testId,
                bodyRegion = assessment.bodyRegionName,
                Date = assessment.creationDate,
                exerciseCount = assessment.totalExercise,
                isReportReady = assessment.isReportReady,
            )
            Spacer(Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()

            ) {
                Button(
                    onClick = { },
                    modifier = Modifier.width(150.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF19A04F),
                    )
                ) {
                    Text(
                        text = "Edit",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(4.dp),
                    )
                }
                Spacer(Modifier.height(12.dp))
                Button(
                    onClick = { onViewExerciseButtonClicked() },
                    modifier = Modifier.width(150.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFF80F1B),
                    )

                ) {
                    Text(
                        text = "Track",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(4.dp),
                    )
                }
            }
            Spacer(Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier.width(150.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF1176B4),
                    )
                ) {
                    Text(
                        text = "Followup",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(4.dp),
                    )
                }
                Spacer(Modifier.height(12.dp))
                Button(
                    onClick = { },
                    modifier = Modifier.width(150.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFFFC000),
                    )
                ) {
                    Text(
                        text = "Report",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(4.dp),
                    )
                }
            }
            Spacer(Modifier.height(12.dp))
            Button(
                onClick = { },
                modifier = Modifier.width(150.dp),
                shape = CircleShape,
            ) {
                Text(
                    text = "Movements",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(4.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AssessmentCardPreview() {
    EmmaVirtualTherapistTheme {
        AssessmentCard(
            Assessment(
                testId = "TEST-16643",
                creationDate = "2021-05-25",
                isReportReady = false,
                providerId = "123123",
                providerName = "Rashed Monin",
                bodyRegionId = 0,
                bodyRegionName = "Full Body",
                registrationType = "In Clinic",
                totalExercise = 478,
                exercises = emptyList(),
            )
        ) {}
    }
}