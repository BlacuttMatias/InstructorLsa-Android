package com.example.instructorlsa.viewmodels

import com.example.instructorlsa.R
import com.example.instructorlsa.models.User
import com.example.instructorlsa.viewmodels.categories.CategoryType
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import com.example.instructorlsa.viewmodels.games.GameViewModel
import com.example.instructorlsa.viewmodels.games.serviceManager.ComprehensiveGamesServiceManager
import com.example.instructorlsa.viewmodels.games.serviceManager.GamesServiceManager
import com.example.instructorlsa.viewmodels.signs.SignViewModel

object InstructorLsaConfig {
    var categoryLearningViewModel: CategoryViewModel? = null
    var categoryPracticeViewModel: CategoryViewModel? = null
    var learningSigns: List<SignViewModel> = listOf()
    var resultGames: Double = 0.0
    var indexSignToLearn: Int = 0
    var user: User = User(null, null, null, null)
    var currentGames: List<GameViewModel> = listOf()
    var indexCurrentGame: Int = 0
    var comeFromInfoScreen = false
    var gamesAnsweredCorrect = 0
    var categoriesViewModel: List<CategoryViewModel> = listOf()
    var gamesServiceManager: GamesServiceManager = ComprehensiveGamesServiceManager()

    fun setLearningCategory(category: CategoryViewModel){
        categoryLearningViewModel = category
    }

    fun getLearningCategory(): CategoryViewModel{
        return categoryLearningViewModel ?:  CategoryViewModel(0,"Colores", CategoryType.Colors, R.drawable.ic_color_pencils, true)
    }

    fun setPracticeCategory(category: CategoryViewModel){
        categoryPracticeViewModel = category
    }

    fun getPracticeCategory(): CategoryViewModel{
        return categoryPracticeViewModel ?:  CategoryViewModel(0,"Colores", CategoryType.Colors, R.drawable.ic_color_pencils, true)
    }

    fun getUserToken(): String{
        return user.token ?: ""
    }

    fun restartValueGames(){
        currentGames = listOf()
        indexCurrentGame = 0
        comeFromInfoScreen = false
        gamesAnsweredCorrect = 0
    }

    fun getLearningCategories(): List<CategoryViewModel>{
        return categoriesViewModel.map { it.copy(enabled = true) }
    }

    fun getUserEmail(): String{
        return user.email ?: ""
    }

    fun isPracticeFlow(): Boolean{
        return gamesServiceManager.isPractice()
    }
}