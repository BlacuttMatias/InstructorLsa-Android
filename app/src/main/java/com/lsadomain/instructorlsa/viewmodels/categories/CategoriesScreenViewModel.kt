package com.lsadomain.instructorlsa.viewmodels.categories

import androidx.navigation.NavController
import com.lsadomain.instructorlsa.viewmodels.common.ScreenWithAlertInfoViewModel

class CategoriesScreenViewModel(
    navigationStrategy: CategoryNavigationStrategy,
    categoriesViewModel: List<CategoryViewModel>
): ScreenWithAlertInfoViewModel() {

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

    override fun shouldShowInfoButton(): Boolean{
        return navigationStrategy.shouldShowInfoButton()
    }

    override fun getBodyTextAlertInfo(): String{
        return "Cada categoría se desbloqueará una vez que hayas completado la misma en la Sección Aprendizaje."
    }
}
