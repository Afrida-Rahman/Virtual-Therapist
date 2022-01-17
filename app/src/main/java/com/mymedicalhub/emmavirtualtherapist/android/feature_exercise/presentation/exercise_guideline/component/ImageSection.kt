package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exercise_guideline.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun ImageSection() {
    val images = listOf(1)
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = "Exercise Image", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        if (images.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(images) {
                    Image(
                        painter = painterResource(id = R.drawable.exercise),
                        contentDescription = null,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
        } else {
            Text(text = "Opps! Could not find any image for this exercise.")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageSectionPreview() {
    EmmaVirtualTherapistTheme {
        ImageSection()
    }
}