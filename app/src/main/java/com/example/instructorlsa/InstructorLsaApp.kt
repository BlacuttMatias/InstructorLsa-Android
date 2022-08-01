package com.example.instructorlsa

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.instructorlsa.ui.screens.categories.CategoriesScreen
import com.example.instructorlsa.ui.screens.home.HomeScreen
import com.example.instructorlsa.ui.screens.learningSection.signs.SignsScreen
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import com.example.instructorlsa.viewmodels.signs.MockDataSigns
import com.example.instructorlsa.viewmodels.signs.SignsScreenViewModel


@Composable
fun InstructorLsaApp() {
    Navigation()
}

@Composable
fun Navigation() {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home") {
        composable(NavigationRoute.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(NavigationRoute.CategoriesLearning.route){
            CategoriesScreen(navController)
        }
        composable(NavigationRoute.Signs.route){
            SignsScreen(navController = navController, screenViewModel = SignsScreenViewModel(
                category = CategoryViewModel("Colores", 0),
                signs = MockDataSigns.signs
            ))
        }
    }
}

enum class NavigationRoute(val route: String){
    Home("Home"),
    CategoriesLearning("learning/categories"),
    Signs("learning/signs")
}