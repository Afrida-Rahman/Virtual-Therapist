package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.component.BottomNavigationBarModified
import com.mymedicalhub.emmavirtualtherapist.android.core.component.CustomTopAppBar
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.welcome.components.ActionCard
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun DashboardScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CustomTopAppBar(
                navController = navController,
                showNotificationIcon = true
            )
        },
        bottomBar = {
            BottomNavigationBarModified(navController = navController)
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            ActionCard(
                icon = R.drawable.posture_screening,
                text = "Posture Screening",
                backgroundColor = Color(0xFF1176B4),
                onClick = { }
            )

            Spacer(modifier = Modifier.height(24.dp))
            ActionCard(
                icon = R.drawable.user,
                text = "Fysical Score™  \n" +
                        "Screening",
                backgroundColor = Color(0xFF19A04F),
                onClick = { }
            )

            Spacer(modifier = Modifier.height(24.dp))
            ActionCard(
                icon = R.drawable.fysical_score,
                text = "Posture or \n" +
                        "Fysical Score™  \n" +
                        "Results",
                backgroundColor = Color(0xFFFFCC00),
                onClick = { }
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    EmmaVirtualTherapistTheme {
        DashboardScreen(rememberNavController())
    }
}
