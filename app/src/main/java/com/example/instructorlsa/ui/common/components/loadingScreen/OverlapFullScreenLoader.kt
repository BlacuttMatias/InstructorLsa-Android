package com.example.instructorlsa.ui.common.components.loadingScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun OverlapFullScreenLoader(showLoader: Boolean, content: @Composable() (BoxScope.() -> Unit)) {
    Box(modifier = Modifier.fillMaxSize()){
        if(showLoader){
            FullScreenLoader(
                modifier = Modifier.fillMaxSize()
                    .zIndex(2f)
                    .background(MaterialTheme.colors.background)
            )
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            content = content
        )

    }
}