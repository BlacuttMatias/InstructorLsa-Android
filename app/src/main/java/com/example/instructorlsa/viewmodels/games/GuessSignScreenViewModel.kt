package com.example.instructorlsa.viewmodels.games

import com.example.instructorlsa.viewmodels.categories.CategoryViewModel

class GuessSignScreenViewModel(game: GameViewModel, category: CategoryViewModel) {
    val game: GameViewModel
    val category: CategoryViewModel

    init{
        this.game = game
        this.category = category
    }
}