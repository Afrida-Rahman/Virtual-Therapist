package com.mymedicalhub.emmavirtualtherapist.android.core.component

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(elevation = 8.dp) {
        BottomNavigationItem(
            selected = true,
            onClick = {
                navController.popBackStack()
                navController.navigate(Screen.DashboardScreen.route)
            },
            label = { Text(text = "Home") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.home_new),
                    contentDescription = "Home"
                )
            }
        )
        BottomNavigationItem(
            selected = true,
            onClick = {
                navController.popBackStack()
                navController.navigate(Screen.AssessmentListScreen.route)
            },
            label = { Text(text = "Assessments") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.assessments),
                    contentDescription = "Assessments"
                )
            }
        )
        BottomNavigationItem(
            selected = true,
            onClick = {
                navController.popBackStack()
            },
            label = { Text(text = "Calendar") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = "Calendar"
                )
            }
        )
        BottomNavigationItem(
            selected = true,
            onClick = {
                navController.popBackStack()
            },
            label = { Text(text = "Report") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.report),
                    contentDescription = "Report"
                )
            }
        )
    }
}

