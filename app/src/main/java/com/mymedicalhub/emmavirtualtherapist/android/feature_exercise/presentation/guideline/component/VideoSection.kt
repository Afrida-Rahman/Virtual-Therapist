package com.mymedicalhub.emmavirtualtherapist.android.feature_exercise.presentation.guideline.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView
import com.mymedicalhub.emmavirtualtherapist.android.ui.theme.EmmaVirtualTherapistTheme

@Composable
fun VideoSection(videoUrl: String?) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Exercise Video", style = MaterialTheme.typography.h2)

        videoUrl?.let {
            if (it.isEmpty()) {
                Text(text = "No video is available")
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PlayVideo(videoUrl)
                }
            }
        }
    }
}

@Composable
fun PlayVideo(url: String) {
    val context = LocalContext.current
    val player = ExoPlayer.Builder(context).build()
    val playerView = PlayerView(context)
    val mediaItem = MediaItem.fromUri(url)
    val playWhenReady by rememberSaveable {
        mutableStateOf(false)
    }
    player.setMediaItem(mediaItem)
    playerView.player = player
    LaunchedEffect(player) {
        player.prepare()
        player.playWhenReady = playWhenReady
        player.volume = 0f
    }
    AndroidView(factory = {
        playerView
    })
}

@Preview(showBackground = true)
@Composable
fun VideoSectionPreview() {
    EmmaVirtualTherapistTheme {
        VideoSection(videoUrl = "")
    }
}
