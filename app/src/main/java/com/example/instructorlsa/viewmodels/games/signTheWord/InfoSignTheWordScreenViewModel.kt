package com.example.instructorlsa.viewmodels.games.signTheWord

import androidx.lifecycle.ViewModel
import com.example.instructorlsa.constants.Constants
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
            "Recordá que las manos y la cara deben salir en la grabación en todo momento",
            "La secuencia para grabarte debería ser la siguiente: comenzar con la manos abajo, realizar la seña y luego terminar con las manos abajo",
            "Tendrás un máximo de " + Constants.secondsToTakeVideo.toString() + " segundos para grabarte haciendo la seña",
            "Deberás alejarte de la cámara lo suficiente para que se vea desde tu cintura hasta tu cabeza"
        )
    }
}