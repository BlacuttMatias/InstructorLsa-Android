package com.example.instructorlsa

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instructorlsa.ui.screens.categories.CategoriesScreen
import com.example.instructorlsa.ui.screens.home.HomeScreen
import com.example.instructorlsa.ui.screens.learningSection.signLearning.SignLearningScreen
import com.example.instructorlsa.ui.screens.learningSection.signs.SignsScreen
import com.example.instructorlsa.ui.screens.login.LoginScreen
import com.example.instructorlsa.ui.screens.practiceSection.startPractice.GameScreen
import com.example.instructorlsa.ui.screens.practiceSection.startPractice.StartPracticeScreen
import com.example.instructorlsa.viewmodels.InstructorLsaConfig
import com.example.instructorlsa.viewmodels.categories.CategoriesScreenViewModel
import com.example.instructorlsa.viewmodels.categories.CategoryLearningNavigation
import com.example.instructorlsa.viewmodels.categories.CategoryPracticeNavigation
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import com.example.instructorlsa.viewmodels.games.GameScreenViewModel
import com.example.instructorlsa.viewmodels.signs.MockDataSigns
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
            HomeScreen(navController = navController)
        }
        composable(NavigationRoute.CategoriesLearning.route){
            val screenViewModel = CategoriesScreenViewModel(navigationStrategy = CategoryLearningNavigation())
            CategoriesScreen(navController, screenViewModel = screenViewModel)
        }
        composable(NavigationRoute.Signs.route){
            SignsScreen(navController = navController, screenViewModel = SignsScreenViewModel(
                category = InstructorLsaConfig.getLearningCategory()
            ))
        }
        composable(NavigationRoute.SignLearning.route){
            SignLearningScreen(navController = navController, screenViewModel = SignLearningScreenViewModel(
                category = CategoryViewModel("Colores", 0),
                signs = InstructorLsaConfig.learningSigns,
                currentIndex = InstructorLsaConfig.indexSignToLearn
            ))
        }
        composable(NavigationRoute.CategoriesPractice.route){
            val screenViewModel = CategoriesScreenViewModel(navigationStrategy = CategoryPracticeNavigation())
            CategoriesScreen(navController, screenViewModel = screenViewModel)
        }
        composable(NavigationRoute.StartPractice.route){
            val screenViewModel = StartPracticeViewModel(category = InstructorLsaConfig.getPracticeCategory())
            StartPracticeScreen(navController = navController, screenViewModel)
        }
        composable(NavigationRoute.GamePractice.route){
            val screenViewModel = GameScreenViewModel(category = InstructorLsaConfig.getPracticeCategory())
            GameScreen(navController = navController, screenViewModel = screenViewModel)
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
    GamePractice("practice/game")
}