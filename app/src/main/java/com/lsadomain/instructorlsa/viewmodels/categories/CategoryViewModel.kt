package com.lsadomain.instructorlsa.viewmodels.categories

data class CategoryViewModel(
    val id: Int = 0,
    val name: String,
    val type: CategoryType = CategoryType.Unknown,
    val iconId: Int,
    val enabled: Boolean = true
)

enum class CategoryType(val categoryName: String){
    Numbers("numeros"),
    Alphabet("abecedario"),
    Manners("modales"),
    Foods("comidas"),
    People("personas"),
    Verbs("verbos"),
    Questions("preguntas"),
    Colors("colores"),
    Geography("geografia"),
    Unknown("unknown")
}