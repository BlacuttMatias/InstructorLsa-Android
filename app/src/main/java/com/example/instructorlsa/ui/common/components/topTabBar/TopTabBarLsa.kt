package com.example.instructorlsa.ui.common.components.topTabBar

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import com.example.instructorlsa.NavigationRoute
import com.example.instructorlsa.R
import com.example.instructorlsa.googleApi.GoogleSignInClient

@Composable
fun TopTabBarLsa(titleText: String, navController: NavController?, showCloseSession: Boolean = true, showInfoButton: Boolean = false){
    var showMenu by remember { mutableStateOf(false) }
    val closeSessionText = stringResource(id = R.string.close_session)
    val infoGameText = stringResource(id = R.string.info_sign_the_word_game)
    val context = LocalContext.current
    TopAppBar(
        title = { TitleTopBarText(text = titleText) },
        navigationIcon = if (navController?.previousBackStackEntry != null) {
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        } else {
            null
        },
        actions = {
            if(showCloseSession){
                IconButton(onClick = { showMenu = !showMenu }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = ""
                    )
                }
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false }) {
                    if(showInfoButton){
                        DropdownMenuItem(onClick = {
                            navController?.navigate(NavigationRoute.InfoGame.route)
                        }) {
                            Text(text = infoGameText)
                        }
                    }
                    DropdownMenuItem(onClick = {
                        GoogleSignInClient.getClient(context).signOut().addOnCompleteListener {
                            navController?.navigate(NavigationRoute.Login.route){
                                popUpTo(0)
                            }
                        }
                    }) {
                        Text(text = closeSessionText)
                    }
                }
            }
        }

    )
}