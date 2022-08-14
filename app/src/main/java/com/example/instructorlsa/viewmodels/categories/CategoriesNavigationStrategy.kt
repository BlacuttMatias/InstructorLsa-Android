package com.example.instructorlsa.viewmodels.categories

import androidx.navigation.NavController
import com.example.instructorlsa.NavigationRoute
import com.example.instructorlsa.viewmodels.InstructorLsaConfig

interface CategoryNavigationStrategy{
    fun navigateToNextScreen(navController: NavController, category: CategoryViewModel)
}

class CategoryLearningNavigation: CategoryNavigationStrategy{
    override fun navigateToNextScreen(navController: NavController, category: CategoryViewModel) {
        InstructorLsaConfig.setLearningCategory(category)
        navController.navigate(NavigationRoute.Signs.route)
    }
}

class CategoryPracticeNavigation: CategoryNavigationStrategy{
    override fun navigateToNextScreen(navController: NavController, category: CategoryViewModel) {
        InstructorLsaConfig.setPracticeCategory(category)
        navController.navigate(NavigationRoute.StartPractice.route)
    }
}
