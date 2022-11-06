package com.lsadomain.instructorlsa.viewmodels.startPractice

import com.lsadomain.instructorlsa.viewmodels.InstructorLsaConfig
import com.lsadomain.instructorlsa.viewmodels.categories.CategoryViewModel
import com.lsadomain.instructorlsa.viewmodels.games.serviceManager.PracticeGamesServiceManager

class StartPracticeViewModel(category: CategoryViewModel) {
    val category: CategoryViewModel

    init{
        this.category = category
        InstructorLsaConfig.gamesServiceManager = PracticeGamesServiceManager(category = category)
    }
}