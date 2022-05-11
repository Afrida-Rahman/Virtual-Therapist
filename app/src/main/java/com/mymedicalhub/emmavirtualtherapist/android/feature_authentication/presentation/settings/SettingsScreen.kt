package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.component.CustomTopAppBar
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.settings.component.AccountSettingSection
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.settings.component.GeneralSettingSection
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.settings.component.ProfileHeader
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.settings.component.SettingsItem
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Red
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Red200

@Composable
fun SettingsScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                leadingIcon = R.drawable.ic_arrow_back,
                onClickLeadingIcon = { navController.popBackStack() }
            ) {
                Text(text = "Settings", fontWeight = FontWeight.Bold)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            ProfileHeader(
                fullName = "Minerva Janero",
                email = "janero@yopmail.com",
                tenant = "emma"
            )
            AccountSettingSection(navController = navController)
            GeneralSettingSection(navController = navController)
            Spacer(modifier = Modifier.height(46.dp))
            SettingsItem(
                text = "Sign Out",
                textColor = Red,
                leadingIcon = R.drawable.log_out,
                leadingIconColor = Red,
                trailingIcon = R.drawable.arrow_forward,
                iconBackgroundColor = Red200,
                onClick = {
                    navController.popBackStack()
                    navController.popBackStack()
                    navController.navigate(Screen.SignInScreen.route)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    EmmaVirtualTherapistTheme {
        SettingsScreen(rememberNavController())
    }
}