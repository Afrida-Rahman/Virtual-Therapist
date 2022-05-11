package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.notification

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.component.BottomNavigationBar
import com.mymedicalhub.emmavirtualtherapist.android.core.component.CustomTopAppBar
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Blue
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun NotificationScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CustomTopAppBar(
                leadingIcon = R.drawable.ic_arrow_back,
                onClickLeadingIcon = {},
            ) {
                Text(
                    text = "Notifications",
                    style = MaterialTheme.typography.h3
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                "New Notification",
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(20.dp))
            Icon(
                painter = painterResource(R.drawable.calendar_filled),
                contentDescription = "Calendar",
                tint = Blue,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(
                        BorderStroke(color = Color(117, 138, 223), width = 1.dp),
                        shape = CircleShape
                    )
                    .padding(10.dp)
            )
            Spacer(modifier = Modifier.height(42.dp))
            Icon(
                painter = painterResource(R.drawable.report_filled),
                contentDescription = "Report",
                tint = Blue,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(
                        BorderStroke(color = Color(117, 138, 223), width = 1.dp),
                        shape = CircleShape
                    )
                    .padding(10.dp)
            )
            Spacer(modifier = Modifier.height(42.dp))
            Row(
                modifier = Modifier
            )
            {
                Icon(
                    painter = painterResource(R.drawable.assessments_filled),
                    contentDescription = "Assessments",
                    tint = Blue,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(
                            BorderStroke(color = Color(117, 138, 223), width = 1.dp),
                            shape = CircleShape
                        )
                        .padding(10.dp)
                )
                Column(

                ) {
                    Text(
                        "MyMedicalHub",
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier
                            .padding(start = 20.dp),
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        "Your report is ready to download",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .padding(start = 20.dp),
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        "MyMedicalHub",
                        modifier = Modifier
                            .padding(start = 20.dp),
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotificationScreenPreview() {
    EmmaVirtualTherapistTheme {
        NotificationScreen(rememberNavController())
    }
}
