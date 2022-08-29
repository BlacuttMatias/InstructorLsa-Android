package com.example.instructorlsa.ui.screens.learningSection.signLearning.components

import android.os.Build
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView


@Composable
fun VideoPlayer(urlVideo: String, playWhenReady: Boolean = false, repeatVideo: Boolean = false){
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            CookieManager.getInstance().setAcceptCookie(true)
            if (Build.VERSION.SDK_INT >= 21) {
                CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)
            }

            val webSettings: WebSettings = this.getSettings()
            webSettings.javaScriptEnabled = true
            webSettings.domStorageEnabled = true

            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setJavaScriptEnabled(true);
            webSettings.setUseWideViewPort(true);
            webSettings.setSaveFormData(false);
            webSettings.setDomStorageEnabled(true);
            webSettings.setAllowFileAccess(true);
            webSettings.setPluginState(WebSettings.PluginState.ON);

            this.setWebViewClient(WebViewClient())
            loadUrl(urlVideo)
        }
    }, update = {
        it.loadUrl(urlVideo)
    },
        modifier = Modifier.fillMaxWidth().height(250.dp)
    )
}

// For displaying preview in
// the Android Studio IDE emulator
@Preview(showBackground = true)
@Composable
fun VideoPlayerPreview() {
    VideoPlayer(urlVideo = "https://cdn.videvo.net/videvo_files/video/free/2020-05/large_watermarked/3d_ocean_1590675653_preview.mp4")
}