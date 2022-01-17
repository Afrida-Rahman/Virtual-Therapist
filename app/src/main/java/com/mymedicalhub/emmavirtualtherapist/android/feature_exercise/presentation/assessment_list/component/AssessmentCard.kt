package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.assessment_list.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.domain.model.Assessment
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun AssessmentCard(assessment: Assessment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .shadow(4.dp, RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            AssessmentCardHeader(
                assessment.testId,
                assessment.creationDate,
                assessment.isReportReady
            )
            Spacer(Modifier.height(12.dp))
            AssessmentCardBody(
                providerName = assessment.providerName,
                bodyRegion = assessment.bodyRegionName,
                registrationType = assessment.registrationType,
                exerciseCount = assessment.exercises.size
            )
            Spacer(Modifier.height(12.dp))
            Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "View Assigned Exercises",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(4.dp)
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
                exercises = emptyList(),
            )
        )
    }
}