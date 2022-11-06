package com.lsadomain.instructorlsa.ui.screens.login

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lsadomain.instructorlsa.NavigationRoute
import com.lsadomain.instructorlsa.googleApi.GoogleApiContract
import com.lsadomain.instructorlsa.ui.screens.login.components.LoginView
import com.lsadomain.instructorlsa.ui.theme.InstructorLsaTheme
import com.lsadomain.instructorlsa.viewmodels.login.LoginScreenViewModel
import com.google.android.gms.common.api.ApiException


@Composable
fun LoginScreen(navController: NavController) {

    val signInRequestCode = 1
    val context = LocalContext.current

    val mSignInViewModel by remember { mutableStateOf(LoginScreenViewModel(context))}

    val authResultLauncher =
        rememberLauncherForActivityResult(contract = GoogleApiContract()) { task ->
            try {
                val gsa = task?.getResult(ApiException::class.java)
                if (gsa != null) {
                    mSignInViewModel.fetchSignInUser(
                        email = gsa.email,
                        firstName = gsa.givenName,
                        lastName = gsa.familyName,
                        token = gsa.id
                    )
                } else {
                    Log.d("Falla Google", "12323123133")
                    mSignInViewModel.showError()
                }
            } catch (e: ApiException) {
                mSignInViewModel.showError()
                Log.d("Error in AuthScreen%s", e.toString())
            }
        }

    LoginView(
        navController = navController,
        onClick = {
            authResultLauncher.launch(signInRequestCode)
        },
        isError = mSignInViewModel.isError,
        mSignInViewModel = mSignInViewModel
    )

    if (mSignInViewModel.googleUser.token != null) {
        LaunchedEffect(key1 = Unit) {
            navController.navigate(
                NavigationRoute.Home.route
            ) {
                popUpTo(NavigationRoute.Login.route) {
                    inclusive = true
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    InstructorLsaTheme {
        LoginScreen(rememberNavController())
    }
}