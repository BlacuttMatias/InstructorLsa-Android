package com.example.instructorlsa.ui.screens.loginHome

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.instructorlsa.Navigation
import com.example.instructorlsa.R
import com.example.instructorlsa.ui.common.components.MainButton
import com.example.instructorlsa.ui.common.components.SloganFooterText
import com.example.instructorlsa.ui.common.components.TitleText
import com.example.instructorlsa.ui.theme.InstructorLsaTheme


@Composable
fun LoginHomeScreen(navController: NavController) {

    val welcomeText = stringResource(id = R.string.home_login_welcome_message)
    val icon = painterResource(id = R.drawable.ic_icon_app)
    val loginText = stringResource(id = R.string.home_login_login_google)
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(20.dp))
        TitleText(text = welcomeText)
        Spacer(modifier = Modifier.height(40.dp))
        Icon(painter = icon,
            contentDescription = null,
            Modifier
                .size(100.dp)
                .clip(CircleShape),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.height(30.dp))
        MainButton(text = loginText) {

        }
        SloganFooterText()
    }
}

@Preview(showBackground = true)
@Composable
fun HomeLoginScreenPreview() {
    InstructorLsaTheme {
        LoginHomeScreen(rememberNavController())
    }
}