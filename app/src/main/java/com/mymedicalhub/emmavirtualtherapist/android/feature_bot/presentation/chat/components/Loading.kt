package com.mymedicalhub.emmavirtualtherapist.android.feature_bot.presentation.chat.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Green
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.Yellow
import kotlinx.coroutines.delay

@Composable
fun Loading() {
    val circleSize = 16.dp
    val spaceBetween = 10.dp
    val travelDistance = 20.dp
    val circles = listOf(
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) }
    )
    val circleColors = listOf(
        MaterialTheme.colors.primary,
        Green,
        Yellow
    )
    circles.forEachIndexed { index, animate ->
        LaunchedEffect(key1 = animate) {
            delay(index * 100L)
            animate.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1200
                        0.0f at 0 with LinearOutSlowInEasing
                        1.0f at 300 with LinearOutSlowInEasing
                        0.0f at 600 with LinearOutSlowInEasing
                        0.0f at 1200 with LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }
    val circleValues = circles.map { it.value }
    val distance = with(LocalDensity.current) { travelDistance.toPx() }
    val lastCircleIndex = circleValues.size - 1

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
    ) {
        circles.forEachIndexed { index, value ->
            Box(
                modifier = Modifier
                    .size(circleSize)
                    .graphicsLayer {
                        translationY = -value.value * distance
                    }
                    .background(color = circleColors[index], shape = CircleShape)
            )
            if (index != lastCircleIndex) {
                Spacer(modifier = Modifier.width(spaceBetween))
            }
        }
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = "Processing...")
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    EmmaVirtualTherapistTheme {
        Loading()
    }
}