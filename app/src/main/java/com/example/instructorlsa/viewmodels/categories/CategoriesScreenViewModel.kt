package com.example.instructorlsa.viewmodels.categories

import androidx.navigation.NavController
import com.example.instructorlsa.R

class CategoriesScreenViewModel(navigationStrategy: CategoryNavigationStrategy) {

    var navigationStrategy: CategoryNavigationStrategy
    var titleText: String

    init{
        this.navigationStrategy = navigationStrategy
        this.titleText = navigationStrategy.getTitleText()
    }

    fun getAllCategories(): List<CategoryViewModel> {
        return listOf(
            CategoryViewModel("Números", R.drawable.ic_numbers),
            CategoryViewModel("Modales", R.drawable.ic_people_manners),
            CategoryViewModel("Personas", R.drawable.ic_people),
            CategoryViewModel("Abecedario", R.drawable.ic_alphabet),
            CategoryViewModel("Comidas", R.drawable.ic_foods),
            CategoryViewModel("Geografía", R.drawable.ic_argentina),
            CategoryViewModel("Colores", R.drawable.ic_color_pencils),
            CategoryViewModel("Preguntas", R.drawable.ic_questions),
            CategoryViewModel("Verbos", R.drawable.ic_call_verb),
        )
    }

    fun navigateToNextScreen(navController: NavController, category: CategoryViewModel){
        navigationStrategy.navigateToNextScreen(navController = navController, category = category)
    }
}


