package com.example.instructorlsa.ui.screens.practiceSection.Games.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import com.example.instructorlsa.viewmodels.games.CountDownTimerViewModel

@Composable
fun CountDownTimer(
    fontSize: TextUnit = 20.sp,
    size: Dp = 60.dp,
    color: Color = MaterialTheme.colors.primary,
    strokeWidth: Dp = 7.dp,
    viewModel: CountDownTimerViewModel,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ){
        Canvas(modifier = Modifier.size(size)){
            drawArc(
                color = color,
                -90f,
                -(360*viewModel.animatedProgressPercentage),
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = viewModel.progressSeconds(),
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}
