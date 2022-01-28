package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun AssessmentCardBody(
    providerName: String,
    bodyRegion: String,
    registrationType: String,
    exerciseCount: Int
) {
    Column {
        AssessmentCardBodyItem(
            icon = painterResource(R.drawable.ic_doctor),
            title = "Provider Name",
            value = providerName
        )
        AssessmentCardBodyItem(
            icon = painterResource(R.drawable.ic_body_region),
            title = "Body Region",
            value = bodyRegion
        )
        AssessmentCardBodyItem(
            icon = painterResource(R.drawable.ic_registration),
            title = "Registration Type",
            value = registrationType
        )
        AssessmentCardBodyItem(
            icon = painterResource(R.drawable.ic_exercise),
            title = "Total Assigned Home Exercise",
            value = exerciseCount.toString()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AssessmentCardBodyPreview() {
    EmmaVirtualTherapistTheme {
        AssessmentCardBody(
            providerName = "Rashed Monin",
            bodyRegion = "Full Body",
            registrationType = "In Clinic",
            exerciseCount = 477
        )
    }
}