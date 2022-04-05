package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.core.util.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashScreenViewModel = hiltViewModel()) {
    val duration = 2000
    var startAnimation by remember { mutableStateOf(false) }
    val animation = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = duration)
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay((duration - 100).toLong())
        navController.popBackStack()
        when {
            viewModel.isAlreadyLoggedIn.value -> {
                navController.navigate(Screen.AssessmentListScreen.route)
            }
            viewModel.isWalkThroughShown.value -> {
                navController.navigate(Screen.SignInScreen.route)
            }
            else -> {
                navController.navigate(Screen.WalkThroughScreen.route)
            }
        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.weight(1f)) {
            Image(
                painter = painterResource(id = R.drawable.mmh),
                contentDescription = "MyMedicalHUB",
                alpha = animation.value
            )
        }
        Box(
            modifier = Modifier
                .padding(8.dp)
                .width(150.dp)
                .height(8.dp)
                .background(color = Color.Blue, shape = RoundedCornerShape(4.dp))
        )
    }
}