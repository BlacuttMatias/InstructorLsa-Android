package com.lsadomain.instructorlsa.constants

import com.lsadomain.instructorlsa.R
import com.lsadomain.instructorlsa.viewmodels.categories.CategoryType
import com.lsadomain.instructorlsa.viewmodels.categories.CategoryViewModel

object Constants {
    val secondsToTakeVideo = 4
    val allBaseCategories = listOf(
        CategoryViewModel(0, "Números", CategoryType.Numbers , R.drawable.numbers_img, true),
        CategoryViewModel(1, "Modales", CategoryType.Manners, R.drawable.people_manners_img, true),
        CategoryViewModel(2, "Personas", CategoryType.People,  R.drawable.people_img, true),
        CategoryViewModel(3, "Abecedario", CategoryType.Alphabet,  R.drawable.alphabet_img, true),
        CategoryViewModel(4, "Comidas", CategoryType.Foods, R.drawable.foods_img, true),
        CategoryViewModel(5, "Geografía", CategoryType.Geography, R.drawable.argentina_img, true),
        CategoryViewModel(6, "Colores", CategoryType.Colors, R.drawable.color_pencils_img, true),
        CategoryViewModel(7, "Preguntas", CategoryType.Questions, R.drawable.questions_img, true),
        CategoryViewModel(8, "Verbos", CategoryType.Verbs, R.drawable.call_verb_img, true)
    )
}