package com.example.instructorlsa.viewmodels.games.signTheWord

import androidx.lifecycle.ViewModel
import com.example.instructorlsa.constants.Constants
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import com.example.instructorlsa.viewmodels.games.GameViewModel

class InfoSignTheWordScreenViewModel(
    topTitleBar: String = "Tips para grabarte"
): ViewModel() {
    val topTitleBar: String

    init{
        this.topTitleBar = topTitleBar
    }

    fun getTitleTopBar(): String{
        return topTitleBar
    }

    fun getTitle(): String{
        return "Instrucciones para grabarte"
    }

    fun getMessages(): List<String>{
        return listOf(
            "Es recomendable tener buena iluminación",
            "Te sugerimos que tus manos y tu cara estén en cuadro en todo momento para un mejor reconocimiento",
            "Recomendamos comenzar con la manos abajo, realizar la seña y luego volverlas a bajar",
            "Tendrás un máximo de " + Constants.secondsToTakeVideo.toString() + " segundos para grabarte haciendo la seña",
            "Tratá de alejarte de la cámara lo suficiente para que se vea desde tu cintura hasta tu cabeza"
        )
    }
}