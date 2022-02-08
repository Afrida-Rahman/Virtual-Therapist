package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.component

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun VideoSection(videoUrl: String?) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Exercise Video", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        videoUrl?.let {
            if (it.isEmpty()) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "No video is available")
                }
            } else {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(220.dp)
                        .height(320.dp)
                ) {
                    playVideo(videoUrl)
                }
            }
        }
    }
}

@Composable
fun playVideo(url: String){
    val context = LocalContext.current
    val exoPlayer = remember(context) {
        SimpleExoPlayer.Builder(context).build().apply {
            val dataSourceFactory : DataSource.Factory = DefaultDataSourceFactory(context,
                Util.getUserAgent(context, context.packageName))

            val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(url))

            this.prepare(source)
        }
    }
    AndroidView(factory = { context ->
        PlayerView(context).apply {
            player = exoPlayer
        }
    })
}

@Preview(showBackground = true)
@Composable
fun VideoSectionPreview() {
    EmmaVirtualTherapistTheme {
        VideoSection(videoUrl = "null")
    }
}
