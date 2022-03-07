package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mymedicalhub.emmavirtualtherapist.android.core.util.AUTHENTICATION_ROUTE
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.sign_in.SignInScreen

fun NavGraphBuilder.authenticationNav(navController: NavController) {
    navigation(startDestination = Screen.SignInScreen.route, route = AUTHENTICATION_ROUTE) {
        composable(route = Screen.SignInScreen.route) {
            SignInScreen(navController = navController)
        }
    }
}