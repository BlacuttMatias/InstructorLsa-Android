package com.example.instructorlsa

import android.util.Log
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instructorlsa.ui.screens.categories.CategoriesScreen
import com.example.instructorlsa.ui.screens.comprehensiveSection.StartComprehensiveGamesScreen
import com.example.instructorlsa.ui.screens.home.HomeScreen
import com.example.instructorlsa.ui.screens.learningSection.signLearning.SignLearningScreen
import com.example.instructorlsa.ui.screens.learningSection.signs.SignsScreen
import com.example.instructorlsa.ui.screens.login.LoginScreen
import com.example.instructorlsa.ui.screens.practiceSection.Games.InfoSignTheWordScreen
import com.example.instructorlsa.ui.screens.practiceSection.resultGames.ResultGamesScreen
import com.example.instructorlsa.ui.screens.practiceSection.startPractice.GameScreen
import com.example.instructorlsa.ui.screens.practiceSection.startPractice.StartPracticeScreen
import com.example.instructorlsa.viewmodels.InstructorLsaConfig
import com.example.instructorlsa.viewmodels.categories.CategoriesScreenViewModel
import com.example.instructorlsa.viewmodels.categories.CategoryLearningNavigation
import com.example.instructorlsa.viewmodels.categories.CategoryPracticeNavigation
import com.example.instructorlsa.viewmodels.comprehensiveGamesSection.StartComprehensiveGamesScreenViewModel
import com.example.instructorlsa.viewmodels.games.GameScreenViewModel
import com.example.instructorlsa.viewmodels.games.resultGames.ResultGamesScreenViewModel
import com.example.instructorlsa.viewmodels.games.signTheWord.InfoSignTheWordScreenViewModel
import com.example.instructorlsa.viewmodels.home.HomeScreenViewModel
import com.example.instructorlsa.viewmodels.signs.SignLearningScreenViewModel
import com.example.instructorlsa.viewmodels.signs.SignsScreenViewModel
import com.example.instructorlsa.viewmodels.startPractice.StartPracticeViewModel


@Composable
fun InstructorLsaApp() {
    Navigation()
}

@Composable
fun Navigation() {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationRoute.Login.route) {
        composable(NavigationRoute.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(NavigationRoute.Home.route) {
            val screenViewModel = HomeScreenViewModel()
            HomeScreen(navController = navController, screenViewModel = screenViewModel)
        }
        composable(NavigationRoute.CategoriesLearning.route){
            val screenViewModel = CategoriesScreenViewModel(
                navigationStrategy = CategoryLearningNavigation(),
                categoriesViewModel = InstructorLsaConfig.getLearningCategories()
            )
            CategoriesScreen(navController, screenViewModel = screenViewModel)
        }
        composable(NavigationRoute.Signs.route){
            SignsScreen(navController = navController, screenViewModel = SignsScreenViewModel(
                category = InstructorLsaConfig.getLearningCategory()
            ))
        }
        composable(NavigationRoute.SignLearning.route){
            SignLearningScreen(navController = navController, screenViewModel = SignLearningScreenViewModel(
                category = InstructorLsaConfig.getLearningCategory(),
                signs = InstructorLsaConfig.learningSigns,
                currentIndex = InstructorLsaConfig.indexSignToLearn
            ))
        }
        composable(NavigationRoute.CategoriesPractice.route){
            val screenViewModel = CategoriesScreenViewModel(
                navigationStrategy = CategoryPracticeNavigation(),
                categoriesViewModel = InstructorLsaConfig.categoriesViewModel
            )
            CategoriesScreen(navController, screenViewModel = screenViewModel)
        }
        composable(NavigationRoute.StartPractice.route){
            val screenViewModel = StartPracticeViewModel(category = InstructorLsaConfig.getPracticeCategory())
            InstructorLsaConfig.restartValueGames()
            StartPracticeScreen(navController = navController, screenViewModel)
        }
        composable(NavigationRoute.GamePractice.route){
            var screenViewModel = GameScreenViewModel(
                category = InstructorLsaConfig.getPracticeCategory(),
                gamesServiceManager = InstructorLsaConfig.gamesServiceManager
            )
            if(InstructorLsaConfig.comeFromInfoScreen){
                screenViewModel = GameScreenViewModel(
                    category = InstructorLsaConfig.getPracticeCategory(),
                    games = InstructorLsaConfig.currentGames,
                    indexCurrentGame = InstructorLsaConfig.indexCurrentGame,
                    gamesAnsweredCorrect = InstructorLsaConfig.gamesAnsweredCorrect,
                    gamesServiceManager = InstructorLsaConfig.gamesServiceManager
                )
            }
            GameScreen(navController = navController, screenViewModel = screenViewModel)
        }
        composable(NavigationRoute.ResultGames.route){
            val screenViewModel = ResultGamesScreenViewModel(
                category = InstructorLsaConfig.getPracticeCategory(),
                result = InstructorLsaConfig.resultGames
            )
            InstructorLsaConfig.restartValueGames()
            ResultGamesScreen(navController = navController, screenViewModel = screenViewModel)
        }
        composable(NavigationRoute.InfoGame.route){
            val screenViewModel = InfoSignTheWordScreenViewModel(category = InstructorLsaConfig.getPracticeCategory())
            InstructorLsaConfig.comeFromInfoScreen = true
            InfoSignTheWordScreen(navController = navController, screenViewModel = screenViewModel)
        }
        composable(NavigationRoute.StartComprehensiveGames.route){
            val screenViewModel = StartComprehensiveGamesScreenViewModel(categories = InstructorLsaConfig.categoriesViewModel)
            InstructorLsaConfig.restartValueGames()
            StartComprehensiveGamesScreen(navController = navController, screenViewModel)
        }
    }
}

enum class NavigationRoute(val route: String){
    Login("Login"),
    Home("Home"),
    CategoriesLearning("learning/categories"),
    Signs("learning/signs"),
    SignLearning("learning/sign"),
    CategoriesPractice("practice/categories"),
    StartPractice("practice/start"),
    ResultGames("practice/resultGames"),
    GamePractice("practice/game"),
    InfoGame("practice/game/info"),
    StartComprehensiveGames("comprehensive/start")
}