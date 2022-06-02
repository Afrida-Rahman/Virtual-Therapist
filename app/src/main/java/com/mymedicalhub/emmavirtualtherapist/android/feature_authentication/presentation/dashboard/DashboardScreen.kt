package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.component.BottomNavigationBar
import com.mymedicalhub.emmavirtualtherapist.android.core.component.CustomTopAppBar
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.welcome.components.ActionCard
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.utils.BotUtils
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun DashboardScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CustomTopAppBar(
                leadingIcon = R.drawable.menu_new,
                onClickLeadingIcon = {
                    navController.navigate(Screen.SettingsScreen.route)
                },
                trailingIcon = R.drawable.notification_bell,
                onClickTrailingIcon = {
                    navController.navigate(Screen.NotificationScreen.route)
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mmh),
                    contentDescription = "MyMedicalHub",
                    modifier = Modifier
                        .height(50.dp)
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            BotUtils.getBots().forEach {
                Spacer(modifier = Modifier.height(24.dp))
                ActionCard(
                    icon = it.icon,
                    text = it.name,
                    backgroundColor = it.backgroundColor,
                    onClick = { navController.navigate(Screen.ChatScreen.withArgs(it.codeName)) }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    EmmaVirtualTherapistTheme {
        DashboardScreen(rememberNavController())
    }
}
