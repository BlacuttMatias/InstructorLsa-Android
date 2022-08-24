package com.example.instructorlsa.viewmodels.categories

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.instructorlsa.NavigationRoute
import com.example.instructorlsa.R
import com.example.instructorlsa.viewmodels.InstructorLsaConfig

interface CategoryNavigationStrategy{
    fun navigateToNextScreen(navController: NavController, category: CategoryViewModel)
    fun getTitleText(): String
}

class CategoryLearningNavigation: CategoryNavigationStrategy{
    override fun navigateToNextScreen(navController: NavController, category: CategoryViewModel) {
        InstructorLsaConfig.setLearningCategory(category)
        navController.navigate(NavigationRoute.Signs.route)
    }

    override fun getTitleText(): String {
        return "Sección Aprendizaje"
    }
}

class CategoryPracticeNavigation: CategoryNavigationStrategy{
    override fun navigateToNextScreen(navController: NavController, category: CategoryViewModel) {
        InstructorLsaConfig.setPracticeCategory(category)
        navController.navigate(NavigationRoute.StartPractice.route)
    }

    override fun getTitleText(): String {
        return "Sección Práctica"
    }
}
