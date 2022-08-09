package com.example.instructorlsa.ui.screens.learningSection.signs.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instructorlsa.R
import com.example.instructorlsa.ui.theme.DarkGreen500
import com.example.instructorlsa.ui.theme.Green500
import com.example.instructorlsa.ui.theme.Grey300
import com.example.instructorlsa.ui.theme.InstructorLsaTheme
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import com.example.instructorlsa.viewmodels.signs.SignViewModel

@Composable
fun SignToLearnView(sign: SignViewModel, onClick: () -> Unit){
    var sign by remember { mutableStateOf(sign) }
    var backgroundColor = MaterialTheme.colors.secondaryVariant
    if (sign.isCompleted){
        if(isSystemInDarkTheme()){
            backgroundColor = DarkGreen500
        }
        else{
            backgroundColor = Green500
        }
    }
    Button(onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 10.dp),
        shape = CircleShape
    ) {
        Text(text = sign.name)
    }
}

@Preview(showBackground = true)
@Composable
fun SignToLearnViewPreview() {
    InstructorLsaTheme {
        SignToLearnView(sign = SignViewModel(id = 1,name = "Diez", urlVideo = "", isCompleted = false), onClick = {})
    }
}