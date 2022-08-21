package com.example.instructorlsa.ui.screens.practiceSection.resultGames.components

import androidx.compose.animation.core.*
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
fun ResultCircleProgressAnimated(
    percentage: Float = 0.75f,
    fontSize: TextUnit = 28.sp,
    size: Dp = 80.dp,
    color: Color = MaterialTheme.colors.primary,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
){
    var progressValue by remember { mutableStateOf(0.0f) }


    val value by animateFloatAsState(
        targetValue = progressValue,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay,
            easing = LinearOutSlowInEasing
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
                360*value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = (value*100).toInt().toString() + "%",
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }

    LaunchedEffect(progressValue) {
        progressValue = percentage
    }
//    var progressValue by remember { mutableStateOf(0.0f) }
//
//
//    val value by animateFloatAsState(
//        targetValue = progressValue,
//        animationSpec = tween(
//            durationMillis = 100,
//            delayMillis = 50,
//            easing = LinearOutSlowInEasing
//        )
//    )
//
//    CircularProgressIndicator(
//        progress = value,
//        modifier = Modifier.size(80.dp)
//    )
//
//    LaunchedEffect(progressValue) {
//        progressValue = 0.75f
//    }

}
