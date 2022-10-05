package com.example.instructorlsa.viewmodels.games.signTheWord

import androidx.lifecycle.ViewModel
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import com.example.instructorlsa.viewmodels.games.GameViewModel

class InfoSignTheWordScreenViewModel(
    category: CategoryViewModel
): ViewModel() {
    val category: CategoryViewModel

    init{
        this.category = category
    }

    fun getTitleTopBar(): String{
        return category.name
    }

    fun getTitle(): String{
        return "Tips para grabarte"
    }

    fun getMessages(): List<String>{
        return listOf(
            "Recuerda que las manos y la cara deben salir en la grabación en todo momento",
            "La secuencia para grabarse deberia ser la siguiente: comenzar con la manos abajo, realizar la seña y luego terminar con las manos abajo",
            "Recuerde hacer la mimica con la boca replicando el acto de decir la palabra oralmente"
        )
    }
}