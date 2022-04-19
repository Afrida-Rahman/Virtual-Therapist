package com.mymedicalhub.emmavirtualtherapist.android.core.component

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(elevation = 8.dp) {
        BottomNavigationItem(
            selected = true,
            onClick = {
                navController.popBackStack()
                navController.navigate(Screen.AssessmentListScreen.route)
            },
            label = { Text(text = "My Assessments") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Dashboard,
                    contentDescription = "My Assessment"
                )
            }
        )
        BottomNavigationItem(
            selected = true,
            onClick = {
                navController.popBackStack()
                navController.navigate(Screen.BotListScreen.route)
            },
            label = { Text(text = "Chat Bots") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Chat,
                    contentDescription = "Chat Bots"
                )
            }
        )
    }
}
