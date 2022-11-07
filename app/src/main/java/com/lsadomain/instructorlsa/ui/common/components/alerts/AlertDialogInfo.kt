package com.lsadomain.instructorlsa.ui.common.components.alerts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lsadomain.instructorlsa.ui.common.components.MainButton

@Composable
fun AlertDialogInfo(isVisible: Boolean,
                    title: String,
                    primaryButtonText: String,
                    onClickContinueButton: () -> Unit,
                    onDismissRequest: () -> Unit = {}
) {
    if(isVisible){
        AlertDialog(
            onDismissRequest = {
                onDismissRequest.invoke()
            },
            //modifier = Modifier.padding(bottom = 170.dp),
            title = {
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(title, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(30.dp))
                }
            },
            shape = RoundedCornerShape(15.dp),
            buttons = {
                Row(
                    modifier = Modifier.padding(
                        vertical = 10.dp
                    ),
                    horizontalArrangement = Arrangement.Center
                ) {
                    MainButton(text = primaryButtonText, fontSize = 16.sp, height = 50.dp) {
                        onClickContinueButton.invoke()
                    }
                }
            }
        )
    }
}