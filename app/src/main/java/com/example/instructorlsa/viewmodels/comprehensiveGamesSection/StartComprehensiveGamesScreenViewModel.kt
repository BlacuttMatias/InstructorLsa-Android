package com.example.instructorlsa.viewmodels.comprehensiveGamesSection

import androidx.lifecycle.ViewModel
import com.example.instructorlsa.viewmodels.InstructorLsaConfig
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import com.example.instructorlsa.viewmodels.games.serviceManager.ComprehensiveGamesServiceManager

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
        return categoriesViewModel.map { it.name }
    }
}