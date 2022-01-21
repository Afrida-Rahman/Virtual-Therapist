package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun ImageSection(imageURLs: List<String>) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Exercise Image", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        if (imageURLs.isNotEmpty()) {
            imageURLs.forEach { _ ->
                Image(
                    painter = painterResource(id = R.drawable.exercise),
                    contentDescription = null,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        } else {
            Text(text = "Opps! Could not find any image for this exercise.")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageSectionPreview1() {
    EmmaVirtualTherapistTheme {
        ImageSection(listOf())
    }
}

@Preview(showBackground = true)
@Composable
fun ImageSectionPreview2() {
    EmmaVirtualTherapistTheme {
        ImageSection(listOf("ads"))
    }
}