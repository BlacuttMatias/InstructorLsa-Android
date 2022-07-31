package com.example.instructorlsa.ui.screens.learningSection.signLearning.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.instructorlsa.R

@Composable
fun BackNavigateButton(onClick: () -> Unit) {
    NavigateButton(iconId = R.drawable.ic_navigate_back, onClick = onClick)
}

@Preview(showBackground = true)
@Composable
fun BackNavigateButtonPreview() {
    BackNavigateButton(){}
}