package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mymedicalhub.emmavirtualtherapist.android.core.util.CHAT_BOT_ROUTE
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.ChatScreen

fun NavGraphBuilder.chatBotNav(navController: NavController) {
    navigation(route = CHAT_BOT_ROUTE, startDestination = Screen.BotListScreen.route) {
        composable(
            route = Screen.ChatScreen.route + "/{codeName}",
            arguments = listOf(
                navArgument(name = "codeName") {
                    type = NavType.StringType
                }
            )
        ) {
            it.arguments?.get("codeName")?.let { codeName ->
                ChatScreen(navController = navController, codeName = codeName.toString())
            }
        }
    }
}