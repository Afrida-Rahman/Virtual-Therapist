package com.mymedicalhub.emmavirtualtherapist.android.core.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.DarkCharcoal

@Composable
fun BottomNavigationBar(navController: NavController, selectedIndex: Int = 1) {
    BottomNavigation(
        elevation = 8.dp,
        backgroundColor = Color.White,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        BottomNavigationItem(
            selected = true,
            onClick = {
                navController.popBackStack()
                navController.navigate(Screen.DashboardScreen.route)
            },
            label = { Text(text = "Home") },
            icon = {
                Icon(
                    painter = painterResource(
                        id = if (selectedIndex == 1) {
                            R.drawable.home_filled
                        } else {
                            R.drawable.home_outline
                        }
                    ),
                    contentDescription = "Home",
                    tint = if (selectedIndex == 1) MaterialTheme.colors.primaryVariant else DarkCharcoal
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
                    contentDescription = "Assessments",
                    tint = if (selectedIndex == 2) MaterialTheme.colors.primaryVariant else DarkCharcoal
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
                    contentDescription = "Calendar",
                    tint = if (selectedIndex == 4) MaterialTheme.colors.primaryVariant else DarkCharcoal
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
                    contentDescription = "Report",
                    tint = if (selectedIndex == 5) MaterialTheme.colors.primaryVariant else DarkCharcoal
                )
            }
        )
    }
}