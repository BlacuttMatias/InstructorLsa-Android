package com.example.instructorlsa.ui.screens.learningSection.signLearning.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun VideoPlayer(urlVideo: String){
    
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

        val context = LocalContext.current
        val player by remember {mutableStateOf(ExoPlayer.Builder(context).build())}
        val playerView by remember {mutableStateOf(StyledPlayerView(context))}
        val mediaItem = MediaItem.fromUri(urlVideo)

        player.setMediaItem(mediaItem)
        playerView.player = player
        LaunchedEffect(player) {
            player.prepare()
        }
        AndroidView(factory = {
            playerView
        })
    }
}

// For displaying preview in
// the Android Studio IDE emulator
@Preview(showBackground = true)
@Composable
fun VideoPlayerPreview() {
    VideoPlayer(urlVideo = "https://cdn.videvo.net/videvo_files/video/free/2020-05/large_watermarked/3d_ocean_1590675653_preview.mp4")
}