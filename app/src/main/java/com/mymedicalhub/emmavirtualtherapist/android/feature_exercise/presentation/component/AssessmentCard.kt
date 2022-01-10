package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun AssessmentCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(8.dp, RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            AssessmentCardHeader("TEST-16643", "2021-05-25", false)
        }
    }
}

@Composable
fun AssessmentCardHeader(testId: String, creationDate: String, isReportReady: Boolean) {
    val reportReadyIcon = if(isReportReady){
        painterResource(id = R.drawable.check)
    } else {
        painterResource(id = R.drawable.crossed)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.report),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(top = 8.dp)
            )
            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    text = testId,
                    fontSize = 30.sp
                )
                Text(text = creationDate, color = Color.Gray)
            }
        }
        Image(
            painter = reportReadyIcon,
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AssessmentCardPreview() {
    EmmaVirtualTherapistTheme {
        AssessmentCard()
    }
}
