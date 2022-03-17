package com.mymedicalhub.emmavirtualtherapist.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mymedicalhub.emmavirtualtherapist.android.core.util.EXERCISE_ROUTE
import com.mymedicalhub.emmavirtualtherapist.android.core.util.ROOT_ROUTE
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.authenticationNav
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chatBotNav
import com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exerciseNav
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
                    startDestination = EXERCISE_ROUTE,
                    route = ROOT_ROUTE
                ) {
                    authenticationNav(navController = navController)
                    exerciseNav(navController = navController)
                    chatBotNav(navController = navController)
                }
            }
        }
    }
}