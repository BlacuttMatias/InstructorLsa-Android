package com.example.instructorlsa.viewmodels.signs

import com.example.instructorlsa.viewmodels.categories.CategoryViewModel

class SignsScreenViewModel(category: CategoryViewModel, signs: List<SignViewModel>) {
    var category: CategoryViewModel
    var signs: List<SignViewModel>

    init {
        this.category = category
        this.signs = signs
    }
}

object MockDataSigns{
    val signs = listOf<SignViewModel>(
        SignViewModel(name = "Rojo", urlVideo = "", isCompleted = true),
        SignViewModel(name = "Azul", urlVideo = "", isCompleted = true),
        SignViewModel(name = "Negro", urlVideo = "", isCompleted = true),
        SignViewModel(name = "Violeta", urlVideo = "", isCompleted = false),
        SignViewModel(name = "Celeste", urlVideo = "", isCompleted = false)
    )
}