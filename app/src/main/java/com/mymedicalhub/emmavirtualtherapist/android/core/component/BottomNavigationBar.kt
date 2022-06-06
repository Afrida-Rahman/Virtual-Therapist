package com.mymedicalhub.emmavirtualtherapist.android.core.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Blue900
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.DarkCharcoal
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Gray500

@Composable
fun BottomNavigationBar(navController: NavController, selectedIndex: Int = 1) {
    BottomNavigation(
        elevation = 8.dp,
        backgroundColor = Color.White
    ) {
        val activeColor = Blue900
        val inactiveColor = Gray500
        val iconSize = 24.dp

        BottomNavigationItem(
            selected = selectedIndex == 1,
            onClick = {
                navController.popBackStack()
                navController.navigate(Screen.DashboardScreen.route)
            },
            label = {
                Text(
                    text = "Home",
                    maxLines = 1
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
                    contentDescription = "Home",
                    tint = if (selectedIndex == 1) activeColor else inactiveColor,
                    modifier = Modifier.size(iconSize)
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
                    maxLines = 1
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
                    tint = if (selectedIndex == 2) activeColor else inactiveColor,
                    modifier = Modifier.size(iconSize)
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
                    maxLines = 1
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
                    contentDescription = "Calendar",
                    tint = if (selectedIndex == 4) activeColor else inactiveColor,
                    modifier = Modifier.size(iconSize)
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
                    maxLines = 1
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
                    contentDescription = "Report",
                    tint = if (selectedIndex == 5) activeColor else inactiveColor,
                    modifier = Modifier.size(iconSize)
                )
            },
            selectedContentColor = MaterialTheme.colors.primaryVariant,
            unselectedContentColor = DarkCharcoal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    EmmaVirtualTherapistTheme {
        BottomNavigationBar(
            navController = rememberNavController(),
            selectedIndex = 1
        )
    }
}