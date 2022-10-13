package com.example.instructorlsa.viewmodels.categories

import androidx.navigation.NavController
import com.example.instructorlsa.R

class CategoriesScreenViewModel(
    navigationStrategy: CategoryNavigationStrategy,
    categoriesViewModel: List<CategoryViewModel>
) {

    var navigationStrategy: CategoryNavigationStrategy
    var titleText: String
    val categoriesViewModel: List<CategoryViewModel>

    init{
        this.navigationStrategy = navigationStrategy
        this.titleText = navigationStrategy.getTitleText()
        this.categoriesViewModel = categoriesViewModel
    }

    fun getAllCategories(): List<CategoryViewModel> {
        return categoriesViewModel
    }

    fun navigateToNextScreen(navController: NavController, category: CategoryViewModel){
        navigationStrategy.navigateToNextScreen(navController = navController, category = category)
    }
}
