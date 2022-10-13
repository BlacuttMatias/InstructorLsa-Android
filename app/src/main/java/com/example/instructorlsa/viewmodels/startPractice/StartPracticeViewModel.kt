package com.example.instructorlsa.viewmodels.startPractice

import com.example.instructorlsa.viewmodels.InstructorLsaConfig
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import com.example.instructorlsa.viewmodels.games.serviceManager.PracticeGamesServiceManager

class StartPracticeViewModel(category: CategoryViewModel) {
    val category: CategoryViewModel

    init{
        this.category = category
        InstructorLsaConfig.gamesServiceManager = PracticeGamesServiceManager(category = category)
    }
}