package com.example.instructorlsa.viewmodels.comprehensiveGamesSection

import androidx.lifecycle.ViewModel
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel

class StartComprehensiveGamesScreenViewModel(
    categories: List<CategoryViewModel>
): ViewModel() {
    var categoriesViewModel: List<CategoryViewModel>

    init{
        this.categoriesViewModel = categories
    }

    fun getBodyIdText(): String{
        return "Aparecerán señas de las siguientes categorías:"
    }

    fun getCategoriesNamesToShow(): List<String>{
        return categoriesViewModel.map { it.name }
    }
}