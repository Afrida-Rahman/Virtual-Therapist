package com.mymedicalhub.emmavirtualtherapist.android.core.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mymedicalhub.emmavirtualtherapist.android.R

@Preview(showSystemUi = true)
@Composable
fun SplashScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.weight(1f)) {
            Image(
                painter = painterResource(id = R.drawable.mmh),
                contentDescription = "MyMedicalHUB"
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