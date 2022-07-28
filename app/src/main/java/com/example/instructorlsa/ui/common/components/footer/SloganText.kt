package com.example.instructorlsa.ui.common.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.instructorlsa.R

@Composable
fun SloganText() {
    val sloganText = stringResource(id = R.string.slogan_app)
    FooterText(text = sloganText)
}