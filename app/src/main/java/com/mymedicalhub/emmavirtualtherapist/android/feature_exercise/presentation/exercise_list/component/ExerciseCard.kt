package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.exercise_list.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun ExerciseCard(
    imageUrl: String?,
    name: String,
    repetition: Int,
    set: Int,
    isActive: Boolean
) {
    val statusIconId = if (isActive) {
        R.drawable.ic_exercise_active
    } else {
        R.drawable.ic_exercise_inactive
    }
    var image = painterResource(id = R.drawable.exercise)
    imageUrl?.let {
        image = painterResource(id = R.drawable.report)
    }
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .padding(bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Box {
                    Image(
                        painter = image,
                        contentDescription = "Exercise Image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(80.dp)
                            .border(2.dp, Color.Gray)
                    )
                    Image(
                        painter = painterResource(id = statusIconId),
                        contentDescription = "Exercise Activation Status",
                        modifier = Modifier.size(15.dp)
                    )
                }
                Column(modifier = Modifier.padding(start = 8.dp)) {
                    Text(text = name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Assigned Repetition: $repetition",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Assigned Set: $set",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.ic_guideline),
                contentDescription = "Guideline Icon Button"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseCardPreview() {
    EmmaVirtualTherapistTheme {
        ExerciseCard(
            imageUrl = null,
            name = "Body Weight Squat",
            repetition = 5,
            set = 3,
            isActive = true,
        )
    }
}