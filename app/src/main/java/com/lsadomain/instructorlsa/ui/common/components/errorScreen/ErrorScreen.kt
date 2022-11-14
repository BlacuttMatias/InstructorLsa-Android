package com.lsadomain.instructorlsa.ui.common.components.errorScreen

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lsadomain.instructorlsa.R
import com.lsadomain.instructorlsa.NavigationRoute
import com.lsadomain.instructorlsa.ui.common.components.FooterColumn
import com.lsadomain.instructorlsa.ui.common.components.MainButton

@Composable
fun ErrorScreen(
    navController: NavController,
    buttonText: String = "Ir al inicio",
    screenRoute: NavigationRoute = NavigationRoute.Home
) {
    val errorTitle = "Algo salió mal"
    val errorBody = "Tuvimos un problema y no podemos continuar. Volvé a intentarlo más tarde"

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter =  painterResource(id = R.drawable.ic_cancel),
            contentDescription = null,
            modifier = Modifier.size(70.dp).clip(CircleShape),
            tint = Color.Red
        )
        Spacer(modifier = Modifier.height(20.dp))
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
        MainButton(text = buttonText) {
            navController.navigate(screenRoute.route){
                popUpTo(0)
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}