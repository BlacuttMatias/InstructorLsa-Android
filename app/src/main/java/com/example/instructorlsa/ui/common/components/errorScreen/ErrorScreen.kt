package com.example.instructorlsa.ui.common.components.errorScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.instructorlsa.ui.common.components.FooterColumn
import com.example.instructorlsa.ui.common.components.MainButton

@Composable
fun ErrorScreen(nacController: NavController) {
    val errorTitle = "Algo salio mal"
    val errorBody = "Tuvimos un problema y no podemos continuar. Vuelva a intentarlo más tarde"
    val errorButtonText = "Ir al inicio"

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = errorTitle,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = errorBody,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 20.dp),
            textAlign = TextAlign.Center
        )
    }
    FooterColumn {
        MainButton(text = errorButtonText) {

        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}