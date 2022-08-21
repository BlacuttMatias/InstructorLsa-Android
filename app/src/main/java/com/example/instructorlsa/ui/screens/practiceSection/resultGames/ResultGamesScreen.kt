package com.example.instructorlsa.ui.screens.practiceSection.resultGames

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
import com.example.instructorlsa.ui.screens.practiceSection.resultGames.components.ResultCircleProgressAnimated
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
            Spacer(modifier = Modifier.height(20.dp))
            TitleText(text = titleText)
            Spacer(modifier = Modifier.height(30.dp))
            ResultCircleProgressAnimated(screenViewModel.result.toFloat())
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = congratulationsText)
            Spacer(modifier = Modifier.height(40.dp))
            MainButton(text = playAgainButtonText) {
                navController.navigate(NavigationRoute.GamePractice.route){
                    popUpTo(NavigationRoute.ResultGames.route) {
                        inclusive = true
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            MainButton(text = mainMenuButtonText) {
                navController.navigate(NavigationRoute.Home.route) {
                    popUpTo(0)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            MainButton(text = learningSectionButtonText) {
                navController.navigate(NavigationRoute.CategoriesLearning.route){
                    popUpTo(NavigationRoute.Home.route)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            MainButton(text = selectionCategoryButtonText) {
                navController.navigate(NavigationRoute.CategoriesPractice.route){
                    popUpTo(NavigationRoute.Home.route)
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            SloganFooterText()
        }
    }
}