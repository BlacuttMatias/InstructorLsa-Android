package com.example.instructorlsa.ui.common.components.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OptionButton(text: String, onClick: () -> Unit) {
    Button(modifier = Modifier.fillMaxWidth().height(50.dp).padding(horizontal = 10.dp),
        shape = CircleShape,
        onClick = onClick
    ){
        Text(text = text, fontSize = 18.sp)
    }
}