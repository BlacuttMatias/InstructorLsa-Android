package com.lsadomain.instructorlsa.viewmodels.categories

import androidx.navigation.NavController
import com.lsadomain.instructorlsa.NavigationRoute
import com.lsadomain.instructorlsa.viewmodels.InstructorLsaConfig

interface CategoryNavigationStrategy{
    fun navigateToNextScreen(navController: NavController, category: CategoryViewModel)
    fun getTitleText(): String
    fun shouldShowInfoButton(): Boolean
}

class CategoryLearningNavigation: CategoryNavigationStrategy{
    override fun navigateToNextScreen(navController: NavController, category: CategoryViewModel) {
        InstructorLsaConfig.setLearningCategory(category)
        navController.navigate(NavigationRoute.Signs.route)
    }

    override fun getTitleText(): String {
        return "Sección Aprendizaje"
    }

    override fun shouldShowInfoButton(): Boolean{
        return false
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

    override fun shouldShowInfoButton(): Boolean{
        return true
    }
}
