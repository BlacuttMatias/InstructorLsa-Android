package com.example.instructorlsa.viewmodels.signs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel

class SignLearningScreenViewModel(category: CategoryViewModel, signs: List<SignViewModel>, currentIndex: Int) {
    var category: CategoryViewModel
    var signs by mutableStateOf(listOf<SignViewModel>())
    var currentIndex by mutableStateOf(0)

    init {
        this.category = category
        this.signs = signs
        this.currentIndex = currentIndex
    }

    fun getCurrentSign(): SignViewModel{
        return signs[currentIndex]
    }

    fun didBackButtonClicked(){
        currentIndex--
    }

    fun didNextButtonPClicked(){
        currentIndex++
    }
}
