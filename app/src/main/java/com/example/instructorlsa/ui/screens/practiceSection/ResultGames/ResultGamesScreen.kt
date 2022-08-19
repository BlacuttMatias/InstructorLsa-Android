package com.example.instructorlsa.ui.screens.practiceSection.ResultGames

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.instructorlsa.NavigationRoute
import com.example.instructorlsa.R
import com.example.instructorlsa.ui.common.components.MainButton
import com.example.instructorlsa.ui.common.components.SloganFooterText
import com.example.instructorlsa.ui.common.components.TitleText
import com.example.instructorlsa.ui.common.components.topTabBar.TopTabBarLsa
import com.example.instructorlsa.ui.screens.practiceSection.Games.GuessSignGameScreen
import com.example.instructorlsa.viewmodels.games.GameScreenViewModel
import com.example.instructorlsa.viewmodels.games.resultGames.ResultGamesScreenViewModel

@Composable
fun ResultGamesScreen(navController: NavController, screenViewModel: ResultGamesScreenViewModel) {
    val titleTopTabBarText = screenViewModel.category.name
    val titleText = stringResource(id = R.string.result_games_correct_answers)
    val congratulationsText = stringResource(R.string.result_games_congratulations)
    val playAgainButtonText = stringResource(R.string.result_games_play_again)
    val mainMenuButtonText = stringResource(R.string.result_games_main_menu)
    val learningSectionButtonText = stringResource(R.string.result_games_go_to_learning_section)
    val selectionCategoryButtonText = stringResource(R.string.result_games_go_to_category_selection)

    Scaffold(
        topBar = { TopTabBarLsa(titleText = titleTopTabBarText, navController = navController) }
    ) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
            TitleText(text = titleText)
            Spacer(modifier = Modifier.height(50.dp))
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = congratulationsText)
            Spacer(modifier = Modifier.height(50.dp))
            MainButton(text = playAgainButtonText) {

            }
            Spacer(modifier = Modifier.height(30.dp))
            MainButton(text = mainMenuButtonText) {

            }
            Spacer(modifier = Modifier.height(30.dp))
            MainButton(text = learningSectionButtonText) {

            }
            Spacer(modifier = Modifier.height(30.dp))
            MainButton(text = selectionCategoryButtonText) {

            }
            Spacer(modifier = Modifier.height(30.dp))
            SloganFooterText()
        }
    }
}