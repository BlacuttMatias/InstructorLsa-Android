package com.example.instructorlsa.viewmodels.games.signTheWord

import androidx.lifecycle.ViewModel
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import com.example.instructorlsa.viewmodels.games.GameScreenViewModel
import com.example.instructorlsa.viewmodels.games.GameViewModel

class SignTheWordGameViewModel(
    game: GameViewModel,
    category: CategoryViewModel,
    delegate: GameScreenViewModel
): ViewModel() {
    val game: GameViewModel
    val category: CategoryViewModel
    var delegate: GameScreenViewModel

    init{
        this.game = game
        this.category = category
        this.delegate = delegate
    }

    fun getMainButtonText(): String{
        return "Empezar a grabar"
    }
}