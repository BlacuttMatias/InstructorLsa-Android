package com.example.instructorlsa.viewmodels.games.resultGames

import androidx.lifecycle.ViewModel
import com.example.instructorlsa.R
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel

class ResultGamesScreenViewModel(category: CategoryViewModel, result: Double): ViewModel() {
    val result: Double
    val category: CategoryViewModel

    init{
        this.result = result
        this.category = category
    }

    fun getCongratulationsTextId(): Int{
        if (result > 0.7){
            return R.string.result_games_congratulations
        }
        else if (result > 0.4){
            return R.string.result_games_very_well
        }
        else{
            return R.string.result_games_can_do_better
        }
    }
}