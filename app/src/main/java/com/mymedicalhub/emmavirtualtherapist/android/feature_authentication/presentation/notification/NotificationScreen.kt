package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.component.BottomNavigationBar
import com.mymedicalhub.emmavirtualtherapist.android.core.component.CustomTopAppBar
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Notification
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.NotificationType
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.notification.component.NotificationItem
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.DarkCharcoal
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun NotificationScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val newNotifications: List<Notification> = listOf(
        Notification(
            type = NotificationType.Appointment,
            title = "EMMA",
            description = "Your report is ready to download",
            date = "12 Feb, 2022"
        ),
        Notification(
            type = NotificationType.Progress,
            title = "MyMedicalHub",
            description = "Your report is ready to download"
        ),
        Notification(
            type = NotificationType.Information,
            title = "MyMedicalHub",
            description = "Your report is ready to download",
            tags = listOf(
                "Region: Legs",
                "Type: Self"
            )
        )
    )
    val thisWeekNotifications: List<Notification> = listOf(
        Notification(
            type = NotificationType.Appointment,
            title = "EMMA",
            description = "Your report is ready to download",
            date = "12 Feb, 2022"
        ),
        Notification(
            type = NotificationType.Progress,
            title = "MyMedicalHub",
            description = "Your report is ready to download"
        )
    )
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CustomTopAppBar(
                leadingIcon = R.drawable.ic_arrow_back,
                onClickLeadingIcon = { navController.popBackStack() },
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
        Column {
            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                item {
                    Text(
                        text = "New Notification",
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Bold,
                        color = DarkCharcoal
                    )
                }
                item { Spacer(modifier = Modifier.height(10.dp)) }
                items(newNotifications) {
                    Divider()
                    NotificationItem(notification = it)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                item {
                    Text(
                        text = "This Week",
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Bold,
                        color = DarkCharcoal
                    )
                }
                item { Spacer(modifier = Modifier.height(10.dp)) }
                items(thisWeekNotifications) {
                    Divider()
                    NotificationItem(notification = it)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    EmmaVirtualTherapistTheme {
        NotificationScreen(rememberNavController())
    }
}
