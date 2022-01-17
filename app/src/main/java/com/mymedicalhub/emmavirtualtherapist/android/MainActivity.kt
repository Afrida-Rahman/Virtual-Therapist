package com.mymedicalhub.emmavirtualtherapist.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.sign_in.SignInScreen
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.assessment_list.AssessmentListScreen
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmmaVirtualTherapistTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.SignInScreen.route
                ) {
                    composable(route = Screen.SignInScreen.route) {
                        SignInScreen(navController = navController)
                    }
                    composable(route = Screen.AssessmentListScreen.route) {
                        AssessmentListScreen()
                    }
                }
            }
        }
    }
}
