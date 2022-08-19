package com.example.instructorlsa.ui.screens.practiceSection.ResultGames

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.instructorlsa.NavigationRoute
import com.example.instructorlsa.ui.common.components.topTabBar.TopTabBarLsa
import com.example.instructorlsa.ui.screens.practiceSection.Games.GuessSignGameScreen
import com.example.instructorlsa.viewmodels.games.GameScreenViewModel

@Composable
fun ResultGamesScreen(navController: NavController) {
    val titleTopTabBarText = "Resultados"

    Scaffold(
        topBar = { TopTabBarLsa(titleText = titleTopTabBarText, navController = navController) }
    ) {

    }
}