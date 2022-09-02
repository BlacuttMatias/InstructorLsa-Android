package com.example.instructorlsa.ui.screens.practiceSection.Games.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CountDownTimer(
    percentage: Float = 0.0f,
    play: Boolean = true,
    fontSize: TextUnit = 28.sp,
    size: Dp = 100.dp,
    color: Color = MaterialTheme.colors.primary,
    strokeWidth: Dp = 10.dp,
    animDuration: Int = 30000,
    animDelay: Int = 0
){
    var progressPercentage by remember { mutableStateOf(1.0f) }
    val backgroundCircleColor = MaterialTheme.colors.secondaryVariant

    val animatedProgressPercentage by animateFloatAsState(
        targetValue = progressPercentage,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay,
            easing = LinearEasing
        )
    )
    Box(
        modifier = Modifier.size(size),
        contentAlignment = Alignment.Center
    ){
        Canvas(modifier = Modifier.size(size)){
            drawArc(
                color = color,
                -90f,
                -(360*animatedProgressPercentage),
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        var progressSeconds = (animatedProgressPercentage*animDuration/1000).toInt() + 1
        if (animatedProgressPercentage == 0.0f){
            progressSeconds = 0
        }
        Text(
            text = progressSeconds.toString(),
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
    LaunchedEffect(progressPercentage) {
        progressPercentage = percentage
    }
}
