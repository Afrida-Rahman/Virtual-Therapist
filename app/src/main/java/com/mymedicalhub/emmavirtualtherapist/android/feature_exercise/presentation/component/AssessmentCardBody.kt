package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun AssessmentCardBody(
    testId: String,
    bodyRegion: String,
    Date: String,
    exerciseCount: Int
) {
    Column {
        AssessmentCardBodyItem(
            icon = painterResource(R.drawable.assessments),
            title = "Test ID",
            value = testId
        )
        AssessmentCardBodyItem(
            icon = painterResource(R.drawable.body_region),
            title = "Body Region",
            value = bodyRegion
        )
        AssessmentCardBodyItem(
            icon = painterResource(R.drawable.calendar),
            title = "Date",
            value = Date
        )
        AssessmentCardBodyItem(
            icon = painterResource(R.drawable.report),
            title = "Report",
            value = exerciseCount.toString()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AssessmentCardBodyPreview() {
    EmmaVirtualTherapistTheme {
        AssessmentCardBody(
            testId = "467",
            bodyRegion = "Full Body",
            Date = "09 Feb, 2022",
            exerciseCount = 477
        )
    }
}