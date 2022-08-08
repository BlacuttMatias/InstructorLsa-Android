package com.example.instructorlsa.viewmodels

import com.example.instructorlsa.R
import com.example.instructorlsa.models.User
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import com.example.instructorlsa.viewmodels.signs.SignViewModel

object InstructorLsaConfig {
    var categoryLearningViewModel: CategoryViewModel? = null
    var learningSigns: List<SignViewModel> = listOf()
    var indexSignToLearn: Int = 0
    var user: User = User(null, null, null)

    fun setLearningCategory(category: CategoryViewModel){
        categoryLearningViewModel = category
    }

    fun getLearningCategory(): CategoryViewModel{
        return categoryLearningViewModel ?:  CategoryViewModel("Colores", R.drawable.ic_color_pencils)
    }
}