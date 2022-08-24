package com.example.instructorlsa.viewmodels.startPractice

import com.example.instructorlsa.viewmodels.categories.CategoryViewModel

class StartPracticeViewModel(category: CategoryViewModel) {
    val category: CategoryViewModel

    init{
        this.category = category
    }
}