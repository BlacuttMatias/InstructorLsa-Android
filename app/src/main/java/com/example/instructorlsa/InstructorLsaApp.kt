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


@Composable
fun InstructorLsaApp() {
    Navigation()
}

@Composable
fun Navigation() {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            HomeScreen(navController = navController)
        }
        composable("Categories/learning"){
            CategoriesScreen(navController)
        }
    }
}