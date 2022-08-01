package com.example.instructorlsa.ui.screens.learningSection.signs.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instructorlsa.R
import com.example.instructorlsa.ui.theme.InstructorLsaTheme
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import com.example.instructorlsa.viewmodels.signs.SignViewModel

@Composable
fun SignToLearnView(sign: SignViewModel, onClick: () -> Unit){
    var sign by remember { mutableStateOf(sign) }
    var backgroundColor = Color.LightGray
    if (sign.isCompleted){
        backgroundColor = Color.Green
    }
    Button(onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        shape = CircleShape
    ) {
        Text(text = sign.name)
    }
}

@Preview(showBackground = true)
@Composable
fun SignToLearnViewPreview() {
    InstructorLsaTheme {
        SignToLearnView(sign = SignViewModel(name = "Diez", urlVideo = "", isCompleted = false), onClick = {})
    }
}