package com.lsadomain.instructorlsa.ui.screens.learningSection.signs.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lsadomain.instructorlsa.ui.theme.DarkGreen500
import com.lsadomain.instructorlsa.ui.theme.Green500
import com.lsadomain.instructorlsa.ui.theme.InstructorLsaTheme
import com.lsadomain.instructorlsa.viewmodels.signs.SignViewModel

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
            .height(55.dp)
            .padding(horizontal = 5.dp),
        shape = CircleShape
    ) {
        Text(text = sign.name, textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
fun SignToLearnViewPreview() {
    InstructorLsaTheme {
        SignToLearnView(sign = SignViewModel(id = 1,name = "Diez", urlVideo = "", isCompleted = false), onClick = {})
    }
}