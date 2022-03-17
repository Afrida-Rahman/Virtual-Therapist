package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.core.component.NavigationDrawer
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.components.BotCard
import com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.utils.BotUtils
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BotListScreen(navController: NavController) {
    val botList = BotUtils.getBots()
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Chat Bots") },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu Icon")
                    }
                }
            )
        },
        drawerContent = {
            NavigationDrawer(
                navController = navController,
                coroutineScope = coroutineScope,
                scaffoldState = scaffoldState
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Text(text = "EMMA Bots", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(12.dp))
            LazyVerticalGrid(cells = GridCells.Fixed(2)) {
                items(botList) {
                    BotCard(bot = it) {
                        navController.navigate(Screen.ChatBotScreen.withArgs(it.codeName))
                    }
                }
            }
        }
    }
}