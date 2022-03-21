package com.mymedicalhub.emmavirtualtherapist.android.feature_authentication.presentation.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mymedicalhub.emmavirtualtherapist.android.R

@Preview(showSystemUi = true)
@Composable
fun WelcomeScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Text(text = "Welcome to".uppercase())
        Spacer(modifier = Modifier.height(24.dp))
        Image(painter = painterResource(id = R.drawable.mmh), contentDescription = "MyMedicalHUB")
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Our health technology solution enables physicians to identify patient risk for injuring any of the ove 350 joints in the human body, avoid pain, minimize healthcare costs and improve wellness.",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(24.dp))
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .padding(vertical = 30.dp, horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.VerifiedUser,
                    contentDescription = "Returning Patient",
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(8.dp),
                    tint = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.width(24.dp))
                Text(text = "Returning Patient", fontSize = 20.sp, color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .background(Color.Green)
                    .padding(vertical = 30.dp, horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "New Patient",
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(8.dp),
                    tint = Color.Green
                )
                Spacer(modifier = Modifier.width(24.dp))
                Text(text = "New Patient", fontSize = 20.sp, color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            Text(text = "Don't have account?")
            Text(text = "Sign Up")
        }
    }
}