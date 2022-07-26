package com.lsadomain.instructorlsa.ui.screens.practiceSection.Games.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lsadomain.instructorlsa.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedStateResultIcon(isVisible: Boolean, isPositiveResult: Boolean) {
    var painterId = 0
    var color = Color.Green
    if(isPositiveResult){
        painterId = R.drawable.ic_check_circle
        color = Color.Green
    }
    else{
        painterId = R.drawable.ic_cancel
        color = Color.Red
    }
    AnimatedVisibility(visible = isVisible,
        enter = fadeIn(animationSpec = tween(500))
    ) {
        if(isVisible){
            Icon(painterResource(id = painterId),
                contentDescription = null,
                Modifier.size(60.dp).clip(CircleShape),
                tint = color
            )
        }
    }
}