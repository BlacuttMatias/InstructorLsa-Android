package com.example.instructorlsa.viewmodels.signs

import com.example.instructorlsa.viewmodels.categories.CategoryViewModel

class SignLearningScreenViewModel(category: CategoryViewModel, signs: List<SignViewModel>, currentIndex: Int) {
    var category: CategoryViewModel
    var signs: List<SignViewModel>
    var currentIndex: Int

    init {
        this.category = category
        this.signs = signs
        this.currentIndex = currentIndex
    }

    fun getCurrentSign(): SignViewModel{
        return signs[currentIndex]
    }
}
