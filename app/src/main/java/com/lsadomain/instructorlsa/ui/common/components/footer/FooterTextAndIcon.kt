package com.lsadomain.instructorlsa.ui.common.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun FooterTextAndIcon(text: String, icon: Painter) {
    FooterColumn {
        Icon(painter = icon,
            contentDescription = null,
            Modifier.size(100.dp).clip(CircleShape),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.height(30.dp))
        FooterText(text = text)
        Spacer(modifier = Modifier.height(40.dp))
    }
}