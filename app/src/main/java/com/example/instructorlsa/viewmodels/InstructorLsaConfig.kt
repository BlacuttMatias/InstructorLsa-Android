package com.example.instructorlsa.viewmodels

import com.example.instructorlsa.R
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel

object InstructorLsaConfig {
    var categoryLearningViewModel: CategoryViewModel? = null

    fun setLearningCategory(category: CategoryViewModel){
        categoryLearningViewModel = category
    }

    fun getLearningCategory(): CategoryViewModel{
        return categoryLearningViewModel ?:  CategoryViewModel("Colores", R.drawable.ic_color_pencils)
    }
}