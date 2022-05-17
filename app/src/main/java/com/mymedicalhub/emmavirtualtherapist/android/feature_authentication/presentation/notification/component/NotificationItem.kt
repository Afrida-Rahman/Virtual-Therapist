package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.notification.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.component.Pill
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.Notification
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.domain.model.NotificationType
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.*

@Composable
fun NotificationItem(notification: Notification) {
    val iconId = when (notification.type) {
        NotificationType.Appointment -> R.drawable.calendar_filled
        NotificationType.Progress -> R.drawable.report_filled
        NotificationType.Information -> R.drawable.assessments_filled
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "Notification Icon",
            tint = MaterialTheme.colors.primaryVariant,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(width = 1.dp, color = Gray100, shape = CircleShape)
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column {
            Text(
                text = notification.title,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = notification.description,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Bold,
                color = Green
            )
            if (notification.tags.isNotEmpty()) {
                Spacer(modifier = Modifier.height(6.dp))
                notification.tags.forEach {
                    Spacer(modifier = Modifier.height(2.dp))
                    Pill(
                        text = it,
                        textColor = Color.Black,
                        backgroundColor = Yellow
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                }
            }
            notification.date?.let {
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Date: $it", style = MaterialTheme.typography.caption, color = Gray500)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationItemPreview() {
    EmmaVirtualTherapistTheme {
        NotificationItem(
            notification = Notification(
                type = NotificationType.Appointment,
                title = "EMMA",
                description = "Your report is ready to download",
                date = "12 Feb, 2022",
                tags = listOf(
                    "Region: Legs",
                    "Type: Self"
                )
            )
        )
    }
}