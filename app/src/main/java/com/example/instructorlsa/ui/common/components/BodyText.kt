package com.example.instructorlsa.ui.common.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BodyText(
    text: String,
    modifier: Modifier = Modifier.padding(horizontal = 20.dp),
    textAlign: TextAlign? = TextAlign.Justify,
    fontSize: TextUnit = 15.sp
) {
    Text(
        text = text,
        fontSize = fontSize,
        modifier = modifier,
        textAlign = textAlign
    )
}