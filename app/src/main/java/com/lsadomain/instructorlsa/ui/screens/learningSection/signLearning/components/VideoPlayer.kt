package com.lsadomain.instructorlsa.ui.screens.learningSection.signLearning.components

import android.graphics.Bitmap
import android.os.Build
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import com.lsadomain.instructorlsa.viewmodels.signs.VideoLoaderManager

@Composable
fun VideoPlayer(urlVideo: String, playWhenReady: Boolean = false, repeatVideo: Boolean = false, delegate: VideoLoaderManager?){
    Box(modifier = Modifier.fillMaxWidth().height(250.dp)){
        if(delegate?.videoLoading == true){
            Box(modifier = Modifier.fillMaxWidth().height(250.dp).zIndex(2f).background(
                MaterialTheme.colors.background)) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center)
                )
            }
        }
        Box(modifier = Modifier.fillMaxWidth().height(250.dp)){
            AndroidView(factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    var webViewClient = WebViewClient()
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

                    webViewClient = object: WebViewClient(){
                        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
//                    delegate?.showLoading()
                        }

                        override fun onPageFinished(
                            view: WebView, url: String) {
//                    delegate?.hideLoading()
                        }
                    }

                    this.setWebViewClient(webViewClient)
                    loadUrl(urlVideo)
                }
            }, update = {
                it.loadUrl(urlVideo)
            },
                modifier = Modifier.fillMaxWidth().height(250.dp)
            )
        }
    }
}

// For displaying preview in
// the Android Studio IDE emulator
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun VideoPlayerPreview() {
    VideoPlayer(urlVideo = "https://cdn.videvo.net/videvo_files/video/free/2020-05/large_watermarked/3d_ocean_1590675653_preview.mp4", delegate = VideoLoaderManager())
}