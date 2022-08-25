package com.example.instructorlsa.ui.screens.practiceSection.startPractice

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.instructorlsa.NavigationRoute
import com.example.instructorlsa.R
import com.example.instructorlsa.ui.common.components.FooterSloganAndIcon
import com.example.instructorlsa.ui.common.components.MainButton
import com.example.instructorlsa.ui.common.components.TitleText
import com.example.instructorlsa.ui.common.components.loadingScreen.FullScreenLoader
import com.example.instructorlsa.ui.common.components.topTabBar.TopTabBarLsa
import com.example.instructorlsa.ui.screens.practiceSection.Games.GuessSignGameScreen
import com.example.instructorlsa.ui.screens.practiceSection.Games.WriteTheSignGameScreen
import com.example.instructorlsa.viewmodels.InstructorLsaConfig
import com.example.instructorlsa.viewmodels.games.GameScreenViewModel
import com.example.instructorlsa.viewmodels.games.GameType

@Composable
fun GameScreen(navController: NavController, screenViewModel: GameScreenViewModel) {
    val titleTopTabBarText = screenViewModel.category.name

    Scaffold(
        topBar = { TopTabBarLsa(titleText = titleTopTabBarText, navController = navController) }
    ) {
        if(screenViewModel.isLoading){
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
                    when(screenViewModel.getCurrentGame().type){
                        GameType.GuessTheSign -> {
                            GuessSignGameScreen(screenViewModel = screenViewModel.getGuessSignScreenViewModel(), navController = navController)
                        }
                        GameType.WriteTheSign -> {
                            WriteTheSignGameScreen(screenViewModel = screenViewModel.getWriteSignScreenViewModel(), navController = navController)
                        }
                        GameType.SignTheWord -> {

                        }
                        GameType.Unknown -> {
                            
                        }
                    }

                }
            }
        }
    }
}