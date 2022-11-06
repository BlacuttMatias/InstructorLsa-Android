package com.lsadomain.instructorlsa.viewmodels.games.resultGames

import androidx.lifecycle.ViewModel
import com.lsadomain.instructorlsa.R
import com.lsadomain.instructorlsa.viewmodels.categories.CategoryViewModel

class ResultGamesScreenViewModel(category: CategoryViewModel, result: Double, isPractice: Boolean): ViewModel() {
    val result: Double
    val category: CategoryViewModel
    val isPractice: Boolean

    init{
        this.result = result
        this.category = category
        this.isPractice = isPractice
    }

    fun getTopBarTitle(): String{
        if(isPractice){
            return category.name
        }
        else{
            return "Juegos Integrales"
        }
    }

    fun shouldShowAdditionalButtons(): Boolean{
        return isPractice
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