package com.example.instructorlsa.constants

import com.example.instructorlsa.R
import com.example.instructorlsa.viewmodels.categories.CategoryType
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel

object Constants {
    val secondsToTakeVideo = 6
    val allBaseCategories = listOf(
        CategoryViewModel(0, "Números", CategoryType.Numbers , R.drawable.ic_numbers, true),
        CategoryViewModel(1, "Modales", CategoryType.Manners, R.drawable.ic_people_manners, true),
        CategoryViewModel(2, "Personas", CategoryType.People,  R.drawable.ic_people, true),
        CategoryViewModel(3, "Abecedario", CategoryType.Alphabet,  R.drawable.ic_alphabet, true),
        CategoryViewModel(4, "Comidas", CategoryType.Foods, R.drawable.ic_foods, true),
        CategoryViewModel(5, "Geografía", CategoryType.Geography, R.drawable.ic_argentina, true),
        CategoryViewModel(6, "Colores", CategoryType.Colors, R.drawable.ic_color_pencils, true),
        CategoryViewModel(7, "Preguntas", CategoryType.Questions, R.drawable.ic_questions, true),
        CategoryViewModel(8, "Verbos", CategoryType.Verbs, R.drawable.ic_call_verb, true)
    )
}