package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.guideline.component

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.mymedicalhub.emmavirtualtherapist.android.R
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun ExerciseCard(
    imageUrl: String?,
    name: String,
    repetition: Int,
    set: Int,
    isActive: Boolean,
    onGuidelineButtonClicked: () -> Unit,
    onStartWorkoutButtonClicked: () -> Unit = {},
    onManualTrackingButtonClicked: () -> Unit = {}
) {
    val statusIconId = if (isActive) {
        R.drawable.ic_check1
    } else {
        R.drawable.ic_cross
    }
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .componentRegistry {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder(context))
            } else {
                add(GifDecoder())
            }
        }
        .build()
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(1f)
                ) {
                    Box {
                        if (imageUrl == null) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_human),
                                contentDescription = "Default Exercise Image",
                                modifier = Modifier.size(150.dp),
                                contentScale = ContentScale.Fit
                            )
                        } else {
                            Image(
                                painter = rememberImagePainter(
                                    data = imageUrl,
                                    builder = {
                                        crossfade(true)
                                        placeholder(R.drawable.ic_loading)
                                    },
                                    imageLoader = imageLoader
                                ),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .size(150.dp)
                                    .align(Alignment.Center)
                            )
                        }
                        Image(
                            painter = painterResource(id = statusIconId),
                            contentDescription = "Exercise Activation Status",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        Text(text = name, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Assigned Repetition: $repetition per set",
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Assigned Set: $set",
                            fontSize = 14.sp
                        )
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_guideline),
                    contentDescription = "Guideline Icon Button",
                    modifier = Modifier.clickable { onGuidelineButtonClicked() }
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(onClick = { onStartWorkoutButtonClicked() }) {
                    Text(text = "Start Workout")
                }
                Button(onClick = { onManualTrackingButtonClicked() }) {
                    Text(text = "Manual Tracking")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseCardPreview() {
    EmmaVirtualTherapistTheme {
        com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component.ExerciseCard(
            imageUrl = null,
            name = "Body Weight Squat",
            repetition = 5,
            set = 3,
            isActive = true,
            onGuidelineButtonClicked = {}
        )
    }
}