package com.example.instructorlsa.ui.common.components.topTabBar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TitleTopBarText(text: String){
    Text(text = text, modifier = Modifier.padding(start = 30.dp))
}