package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mymedicalhub.emmavirtualtherapist.android.core.util.AUTHENTICATION_ROUTE
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.dashboard.DashboardScreen
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.reset_password.RecoveryPasswordScreen
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.reset_password.ResetPasswordScreen
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.settings.SettingsScreen
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.sign_in.SignInScreen
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.sign_up.SignUpScreen
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.splash.SplashScreen
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.walkthrough.WalkThroughScreen
import com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.welcome.WelcomeScreen

fun NavGraphBuilder.authenticationNav(navController: NavController) {
    navigation(startDestination = Screen.SplashScreen.route, route = AUTHENTICATION_ROUTE) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.WalkThroughScreen.route) {
            WalkThroughScreen(navController = navController)
        }
        composable(route = Screen.WelcomeScreen.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.SignInScreen.route) {
            SignInScreen(navController = navController)
        }
        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }
        composable(route = Screen.ResetPasswordScreen.route) {
            ResetPasswordScreen(navController = navController)
        }
        composable(route = Screen.RecoveryPasswordScreen.route) {
            RecoveryPasswordScreen(navController = navController)
        }
        composable(route = Screen.DashboardScreen.route) {
            DashboardScreen(navController = navController)
        }
        composable(route = Screen.SettingsScreen.route) {
            SettingsScreen(navController = navController)
        }
    }
}