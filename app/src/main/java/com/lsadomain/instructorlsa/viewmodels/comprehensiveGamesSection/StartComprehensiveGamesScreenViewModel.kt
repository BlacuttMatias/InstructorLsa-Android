package com.lsadomain.instructorlsa.viewmodels.comprehensiveGamesSection

import androidx.lifecycle.ViewModel
import com.lsadomain.instructorlsa.viewmodels.InstructorLsaConfig
import com.lsadomain.instructorlsa.viewmodels.categories.CategoryViewModel
import com.lsadomain.instructorlsa.viewmodels.games.serviceManager.ComprehensiveGamesServiceManager

class StartComprehensiveGamesScreenViewModel(
    categories: List<CategoryViewModel>
): ViewModel() {
    var categoriesViewModel: List<CategoryViewModel>

    init{
        this.categoriesViewModel = categories
        InstructorLsaConfig.gamesServiceManager = ComprehensiveGamesServiceManager()
    }

    fun getBodyIdText(): String{
        return "Aparecerán señas de las siguientes categorías:"
    }

    fun getCategoriesNamesToShow(): List<String>{
        return categoriesViewModel.filter { it.enabled }.map { it.name }
    }
}