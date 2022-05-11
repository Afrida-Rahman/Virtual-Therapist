package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.settings.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.DarkCharcoal
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun AccountSettingSection(navController: NavController) {
    Column {
        Text(
            text = "ACCOUNT SETTING",
            style = MaterialTheme.typography.caption,
            color = DarkCharcoal,
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)
        )
        SettingsItem(
            text = "Edit Profile",
            leadingIcon = R.drawable.user_gray
        )
        SettingsItem(
            text = "Change Password",
            leadingIcon = R.drawable.shield,
            onClick = {
                navController.navigate(Screen.ResetPasswordScreen.route)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AccountSettingSectionPreview() {
    EmmaVirtualTherapistTheme {
        AccountSettingSection(rememberNavController())
    }
}