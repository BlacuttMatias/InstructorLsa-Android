package com.example.instructorlsa.viewmodels.games.resultGames

import androidx.lifecycle.ViewModel
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel

class ResultGamesScreenViewModel(category: CategoryViewModel, result: Double): ViewModel() {
    val result: Double
    val category: CategoryViewModel

    init{
        this.result = result
        this.category = category
    }
}