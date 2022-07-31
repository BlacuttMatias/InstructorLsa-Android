package com.example.instructorlsa.ui.screens.learningSection.signLearning.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instructorlsa.R

@Composable
fun NextNavigateButton(onClick: () -> Unit) {
    NavigateButton(iconId = R.drawable.ic_navigate_next, onClick = onClick)
}

@Preview(showBackground = true)
@Composable
fun NextNavigateButtonPreview() {
    NextNavigateButton(){}
}