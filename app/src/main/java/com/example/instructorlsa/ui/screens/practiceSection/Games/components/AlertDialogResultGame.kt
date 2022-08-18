package com.example.instructorlsa.ui.screens.practiceSection.Games.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instructorlsa.ui.common.components.MainButton

@Composable
fun AlertDialogResultGame(isVisble: Boolean, title: String, onClickContinueButton: () -> Unit, onDismissRequest: () -> Unit = {}) {
    if(isVisble){
        AlertDialog(
            onDismissRequest = {
                onDismissRequest.invoke()
            },
            modifier = Modifier.padding(bottom = 170.dp),
            title = {
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(title, fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.height(60.dp))
            },
            shape = RoundedCornerShape(15.dp),
            buttons = {
                Row(
                    modifier = Modifier.padding(
                        vertical = 10.dp
                    ),
                    horizontalArrangement = Arrangement.Center
                ) {
                    MainButton(text = "Continuar", fontSize = 16.sp, height = 50.dp) {
                        onClickContinueButton.invoke()
                    }
                }
            }
        )
    }
}