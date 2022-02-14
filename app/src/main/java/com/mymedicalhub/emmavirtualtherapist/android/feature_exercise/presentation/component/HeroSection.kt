package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun HeroSection(patientName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .padding(top = 2.dp, bottom = 16.dp, start = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.mmh_logo),
            contentDescription = "MyMedicalHUB",
            modifier = Modifier.size(80.dp)
        )
        Column(
            modifier = Modifier.padding(start = 20.dp)
        ) {
            Text(
                text = "MyMedicalHUB",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Hello $patientName.", color = MaterialTheme.colors.onPrimary)
            Text(text = "I'm Emma.", color = MaterialTheme.colors.onPrimary)
            Text(text = "Your personal exercise assistant.", color = MaterialTheme.colors.onPrimary)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeroSectionPreview() {
    EmmaVirtualTherapistTheme {
        HeroSection("Rashed Momin")
    }
}