package com.example.instructorlsa.ui.screens.practiceSection.startPractice

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.instructorlsa.NavigationRoute
import com.example.instructorlsa.ui.common.components.errorScreen.ErrorScreen
import com.example.instructorlsa.ui.common.components.loadingScreen.FullScreenLoader
import com.example.instructorlsa.ui.common.components.topTabBar.TopTabBarLsa
import com.example.instructorlsa.ui.screens.practiceSection.Games.GuessSignGameScreen
import com.example.instructorlsa.ui.screens.practiceSection.Games.SignTheWordGameScreen
import com.example.instructorlsa.ui.screens.practiceSection.Games.WriteTheSignGameScreen
import com.example.instructorlsa.viewmodels.InstructorLsaConfig
import com.example.instructorlsa.viewmodels.games.GameScreenViewModel
import com.example.instructorlsa.viewmodels.games.GameType

@Composable
fun GameScreen(navController: NavController, screenViewModel: GameScreenViewModel) {
    val titleTopTabBarText = screenViewModel.getTopBarTitle()

    Scaffold(
        topBar = {
            TopTabBarLsa(
                titleText = titleTopTabBarText,
                navController = navController,
                showCloseSession = false,
                showInfoButton = screenViewModel.shouldShowInfoButton(),
                onBackButtonPressed = { screenViewModel.onBackButtonPressed() }
            )
        }
    ) {
        if(screenViewModel.isError){
            ErrorScreen(navController = navController)
        }
        else if(screenViewModel.isLoading){
            FullScreenLoader()
            screenViewModel.loadInitData()
        }
        else{
            if(screenViewModel.allGamesAreCompleted){
                InstructorLsaConfig.resultGames = screenViewModel.getResultGames()
                navController.navigate(NavigationRoute.ResultGames.route){
                    popUpTo(NavigationRoute.GamePractice.route) {
                        inclusive = true
                    }
                }
            }
            else{
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                    BackHandler(true) {
                        screenViewModel.onBackButtonPressed()
                    }
                    when(screenViewModel.getCurrentGame().type){
                        GameType.GuessTheSign -> {
                            //SignTheWordGameScreen(screenViewModel = screenViewModel.getSignWordScreenViewModel(), navController = navController)
                            GuessSignGameScreen(screenViewModel = screenViewModel.getGuessSignScreenViewModel(), navController = navController)
                        }
                        GameType.WriteTheSign -> {
                            //SignTheWordGameScreen(screenViewModel = screenViewModel.getSignWordScreenViewModel(), navController = navController)
                            WriteTheSignGameScreen(screenViewModel = screenViewModel.getWriteSignScreenViewModel(), navController = navController)
                        }
                        GameType.SignTheWord -> {
                            SignTheWordGameScreen(screenViewModel = screenViewModel.getSignWordScreenViewModel(), navController = navController)
                        }
                        GameType.Unknown -> {
                            screenViewModel.showError()
                        }
                    }

                }
            }
        }
    }
}