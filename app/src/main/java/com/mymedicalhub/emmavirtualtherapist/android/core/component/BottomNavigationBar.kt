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
            selected = selectedIndex == 1,
            onClick = {
                navController.popBackStack()
                navController.navigate(Screen.DashboardScreen.route)
            },
            label = {
                Text(
                    text = "Home",
                )
            },
            icon = {
                Icon(
                    painter = painterResource(
                        id = if (selectedIndex == 1) {
                            R.drawable.home_filled
                        } else {
                            R.drawable.home_outline
                        }
                    ),
                    contentDescription = "Home"
                )
            },
            selectedContentColor = MaterialTheme.colors.primaryVariant,
            unselectedContentColor = DarkCharcoal
        )
        BottomNavigationItem(
            selected = selectedIndex == 2,
            onClick = {
                navController.popBackStack()
                navController.navigate(Screen.AssessmentListScreen.route)
            },
            label = {
                Text(
                    text = "Assessments",
                )
            },
            icon = {
                Icon(
                    painter = painterResource(
                        id = if (selectedIndex == 2) {
                            R.drawable.assessments_filled
                        } else {
                            R.drawable.assessments_outlined
                        }
                    ),
                    contentDescription = "Assessments",
                )
            },
            selectedContentColor = MaterialTheme.colors.primaryVariant,
            unselectedContentColor = DarkCharcoal
        )
        BottomNavigationItem(
            selected = selectedIndex == 4,
            onClick = {
                navController.popBackStack()
            },
            label = {
                Text(
                    text = "Calendar",
                )
            },
            icon = {
                Icon(
                    painter = painterResource(
                        id = if (selectedIndex == 4) {
                            R.drawable.calendar_filled
                        } else {
                            R.drawable.calendar_outlined
                        }
                    ),
                    contentDescription = "Calendar"
                )
            },
            selectedContentColor = MaterialTheme.colors.primaryVariant,
            unselectedContentColor = DarkCharcoal
        )
        BottomNavigationItem(
            selected = selectedIndex == 5,
            onClick = {
                navController.popBackStack()
            },
            label = {
                Text(
                    text = "Report",
                )
            },
            icon = {
                Icon(
                    painter = painterResource(
                        id = if (selectedIndex == 5) {
                            R.drawable.report_filled
                        } else {
                            R.drawable.report_outlined
                        }
                    ),
                    contentDescription = "Report"
                )
            },
            selectedContentColor = MaterialTheme.colors.primaryVariant,
            unselectedContentColor = DarkCharcoal
        )
    }
}