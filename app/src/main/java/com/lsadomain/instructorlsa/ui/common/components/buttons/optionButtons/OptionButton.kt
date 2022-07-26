package com.lsadomain.instructorlsa.ui.common.components.buttons

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lsadomain.instructorlsa.ui.theme.Green500
import com.lsadomain.instructorlsa.ui.theme.Red500

@Composable
fun OptionButton(text: String, showState: Boolean = false, isCorrect: Boolean = false, onClick: () -> Unit) {
    val correctBackgroundColor = Green500
    val incorrectBackgroundColor = Red500
    val normalBackgroundColor = MaterialTheme.colors.primary

    val backgroundColorToShow = remember { Animatable(normalBackgroundColor) }

    if(showState){
        LaunchedEffect(Unit) {
            if(isCorrect){
                backgroundColorToShow.animateTo(correctBackgroundColor, animationSpec = tween(500))
            }
            else{
                backgroundColorToShow.animateTo(incorrectBackgroundColor, animationSpec = tween(500))
            }
        }
    }
    else{
        LaunchedEffect(Unit) {
            backgroundColorToShow.animateTo(normalBackgroundColor, animationSpec = tween(0))
        }
    }


    Button(modifier = Modifier.fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 10.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColorToShow.value),
        onClick = onClick
    ){
        Text(text = text, fontSize = 18.sp)
    }
}