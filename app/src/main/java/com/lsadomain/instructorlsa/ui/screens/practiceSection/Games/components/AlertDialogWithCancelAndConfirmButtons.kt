package com.lsadomain.instructorlsa.ui.screens.practiceSection.Games.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AlertDialogWithCancelAndConfirmButtons(
    isVisible: Boolean,
    title: String,
    cancelButtonText: String = "Cancelar",
    confirmButtonText: String = "Aceptar",
    onClickConfirmButton: () -> Unit,
    onClickCancelButton: () -> Unit,
    onDismissRequest: () -> Unit = {}
) {
    if(isVisible){
        AlertDialog(
            onDismissRequest = {
                onDismissRequest.invoke()
            },
            title = {
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(title, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(80.dp))
            },
            shape = RoundedCornerShape(15.dp),
            buttons = {
                Row(
                    modifier = Modifier.padding(vertical = 10.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = { onClickCancelButton.invoke() }
                    ) {
                        Text(text = cancelButtonText, fontSize = 16.sp)
                    }
                    TextButton(modifier = Modifier.padding(end = 10.dp),
                        onClick = { onClickConfirmButton.invoke() }
                    ) {
                        Text(text = confirmButtonText, fontSize = 16.sp)
                    }
                }
            }
        )
    }
}