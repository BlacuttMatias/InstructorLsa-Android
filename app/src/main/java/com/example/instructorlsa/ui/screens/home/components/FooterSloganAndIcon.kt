package com.example.instructorlsa.ui.common.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import com.example.instructorlsa.R

@Composable
fun FooterSloganAndIcon(icon: Painter) {
    val sloganText = stringResource(id = R.string.slogan_app)
    FooterTextAndIcon(text = sloganText, icon = icon)
}