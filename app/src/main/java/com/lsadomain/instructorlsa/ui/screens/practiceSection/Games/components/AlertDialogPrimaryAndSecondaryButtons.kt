package com.lsadomain.instructorlsa.ui.screens.practiceSection.Games.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AlertDialogWPrimaryAndSecondaryButtons(
    isVisible: Boolean,
    title: String,
    secondaryButtonText: String = "Cancelar",
    primaryButtonText: String = "Aceptar",
    onClickPrimaryButton: () -> Unit,
    onClickSecondaryButton: () -> Unit,
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
                        onClick = { onClickSecondaryButton.invoke() }
                    ) {
                        Text(text = secondaryButtonText, fontSize = 16.sp)
                    }
                    Button(modifier = Modifier.padding(end = 10.dp),
                        onClick = { onClickPrimaryButton.invoke() }
                    ) {
                        Text(text = primaryButtonText, fontSize = 16.sp)
                    }
                }
            }
        )
    }
}