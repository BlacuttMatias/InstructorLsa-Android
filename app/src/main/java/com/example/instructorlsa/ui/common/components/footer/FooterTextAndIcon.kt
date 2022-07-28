package com.example.instructorlsa.ui.common.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun FooterTextAndIcon(text: String, icon: Painter) {
    FooterColumn {
        Icon(painter = icon, contentDescription = null)
        Spacer(modifier = Modifier.height(50.dp))
        FooterText(text = text)
        Spacer(modifier = Modifier.height(80.dp))
    }
}