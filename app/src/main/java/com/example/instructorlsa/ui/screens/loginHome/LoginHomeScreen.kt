package com.example.instructorlsa.ui.screens.loginHome

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instructorlsa.R
import com.example.instructorlsa.ui.theme.InstructorLsaTheme

@Composable
fun LoginHomeScreen() {
    val welcomeText = stringResource(id = R.string.home_login_welcome_message)
    val icon = painterResource(id = R.drawable.ic_launcher_foreground)
    val registerText = stringResource(id = R.string.home_login_register_google)
    val loginText = stringResource(id = R.string.home_login_login_google)
    val sloganAppText = stringResource(id = R.string.slogan_app)
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = welcomeText)
        Spacer(modifier = Modifier.height(40.dp))
        Icon(painter = icon,
            contentDescription = null)
        Spacer(modifier = Modifier.height(30.dp))
        Button(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            onClick = {

            }
        ){
            Text(text = registerText)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(modifier =Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            onClick = {

            }
        ){
            Text(text = loginText)
        }
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(text = sloganAppText)
            Spacer(modifier = Modifier.height(80.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    InstructorLsaTheme {
        LoginHomeScreen()
    }
}