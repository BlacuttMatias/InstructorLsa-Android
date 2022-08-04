package com.example.instructorlsa.ui.screens.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.instructorlsa.R
import com.example.instructorlsa.ui.common.components.MainButton
import com.example.instructorlsa.ui.common.components.SloganFooterText
import com.example.instructorlsa.ui.common.components.TitleText
import com.example.instructorlsa.ui.common.components.loadingScreen.FullScreenLoader
import com.example.instructorlsa.viewmodels.login.LoginScreenViewModel

@Composable
fun LoginView(
    onClick: () -> Unit,
    isError: Boolean = false,
    mSignInViewModel: LoginScreenViewModel
) {
    val state = mSignInViewModel.loading.observeAsState()
    val isLoading = state.value
    val welcomeText = stringResource(id = R.string.login_welcome_message)
    val icon = painterResource(id = R.drawable.ic_icon_app)
    val loginText = stringResource(id = R.string.login_sign_in_google)
    Scaffold {
        if (isLoading == true && !isError) {
            FullScreenLoader()
        } else {

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
                    mSignInViewModel.showLoading()
                    onClick.invoke()
                }
                SloganFooterText()
            }

            when {
                isError -> {
                    isError.let {
                        Text(
                            "Error",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.error
                        )
                        mSignInViewModel.hideLoading()
                    }
                }
            }
        }
    }
}
