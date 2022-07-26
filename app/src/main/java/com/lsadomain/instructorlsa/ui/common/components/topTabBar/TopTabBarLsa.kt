package com.lsadomain.instructorlsa.ui.common.components.topTabBar

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.lsadomain.instructorlsa.NavigationRoute
import com.lsadomain.instructorlsa.R
import com.lsadomain.instructorlsa.googleApi.GoogleSignInClient

@Composable
fun TopTabBarLsa(
    titleText: String,
    navController: NavController?,
    showCloseSession: Boolean = true,
    showInfoButton: Boolean = false,
    onBackButtonPressed: () -> Unit = { navController?.navigateUp() },
    onInfoButtonPressed: () -> Unit = { navController?.navigate(NavigationRoute.InfoGame.route) }
){
    var showMenu by remember { mutableStateOf(false) }
    val closeSessionText = stringResource(id = R.string.close_session)
    val context = LocalContext.current
    TopAppBar(
        title = { TitleTopBarText(text = titleText) },
        navigationIcon = if (navController?.previousBackStackEntry != null) {
            {
                IconButton(onClick = { onBackButtonPressed.invoke() }
                ) {
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
            if(showInfoButton){
                IconButton(onClick = {
                    onInfoButtonPressed.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = ""
                    )
                }
            }
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