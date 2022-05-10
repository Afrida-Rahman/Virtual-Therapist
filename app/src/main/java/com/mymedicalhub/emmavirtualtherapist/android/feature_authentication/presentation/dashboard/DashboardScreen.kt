package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.welcome.components.ActionCard
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Blue
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Green
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Yellow

@Composable
fun DashboardScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CustomTopAppBar(
                leadingIcon = R.drawable.menu_new,
                onClickLeadingIcon = {},
                trailingIcon = R.drawable.notification_bell,
                onClickTrailingIcon = {}
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
                backgroundColor = Blue,
                onClick = { }
            )

            Spacer(modifier = Modifier.height(24.dp))
            ActionCard(
                icon = R.drawable.user,
                text = "Fysical Score™  \n" +
                        "Screening",
                backgroundColor = Green,
                onClick = { }
            )

            Spacer(modifier = Modifier.height(24.dp))
            ActionCard(
                icon = R.drawable.fysical_score,
                text = "Posture or \n" +
                        "Fysical Score™  \n" +
                        "Results",
                backgroundColor = Yellow,
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