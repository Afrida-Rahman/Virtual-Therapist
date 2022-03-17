package com.mymedicalhub.emmavirtualtherapist.android.core.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(
    navController: NavController,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0, 0, 0, 0),
                                Color(0, 0, 0, 0x88)
                            ),
                            start = Offset(
                                LocalConfiguration.current.screenWidthDp.toFloat() / 2,
                                0f
                            ),
                            end = Offset(
                                LocalConfiguration.current.screenWidthDp.toFloat() / 2,
                                LocalConfiguration.current.screenHeightDp.toFloat()
                            )
                        )
                    )
            )
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Navigation Drawer",
                    tint = Color.White
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mmh_logo_white),
                    contentDescription = "MMH White Logo",
                    modifier = Modifier.width(170.dp),
                    contentScale = ContentScale.FillWidth
                )
                Column {
                    Text(
                        text = "EMMA",
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "Your personal exercise management assistant",
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clickable {
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                        navController.popBackStack()
                        navController.navigate(Screen.BotListScreen.route)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Chat,
                    contentDescription = "Chat Bots"
                )
                Spacer(modifier = Modifier.width(24.dp))
                Text(text = "Chat Bots")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clickable {
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                        navController.popBackStack()
                        navController.navigate(Screen.AssessmentListScreen.route)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Dashboard,
                    contentDescription = "My Assessments"
                )
                Spacer(modifier = Modifier.width(24.dp))
                Text(text = "My Assessments")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clickable {
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                        navController.popBackStack()
                        navController.navigate(Screen.SignInScreen.route)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Logout, contentDescription = "Logout")
                Spacer(modifier = Modifier.width(24.dp))
                Text(text = "Logout")
            }
        }
        Text(text = "EMMA_v0.0.26", modifier = Modifier.padding(8.dp))
    }
}