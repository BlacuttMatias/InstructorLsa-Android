package com.lsadomain.instructorlsa.ui.screens.learningSection.signLearning.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.lsadomain.instructorlsa.R

@Composable
fun NextNavigateButton(onClick: () -> Unit) {
    NavigateButton(iconId = R.drawable.ic_navigate_next, onClick = onClick)
}

@Preview(showBackground = true)
@Composable
fun NextNavigateButtonPreview() {
    NextNavigateButton(){}
}