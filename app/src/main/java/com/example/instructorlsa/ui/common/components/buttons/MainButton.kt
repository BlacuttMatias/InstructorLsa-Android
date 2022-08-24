package com.example.instructorlsa.ui.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainButton(text: String, height: Dp = 60.dp, fontSize: TextUnit = 18.sp, onClick: () -> Unit) {
    Button(modifier = Modifier.fillMaxWidth().height(height).padding(horizontal = 20.dp),
        shape = CircleShape,
        onClick = onClick
    ){
        Text(text = text, fontSize = fontSize)
    }
}