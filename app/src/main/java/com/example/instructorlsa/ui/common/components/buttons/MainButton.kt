package com.example.instructorlsa.ui.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainButton(text: String, onClick: () -> Unit) {
    Button(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
        onClick = onClick
    ){
        Text(text = text)
    }
}