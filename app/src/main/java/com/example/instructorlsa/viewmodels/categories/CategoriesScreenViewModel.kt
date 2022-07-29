package com.example.instructorlsa.viewmodels.categories

import com.example.instructorlsa.R

class CategoriesScreenViewModel {

    fun getAllCategories(): List<CategoryViewModel> {
        return listOf(
            CategoryViewModel("Números", R.drawable.ic_launcher_foreground),
            CategoryViewModel("Modales", R.drawable.ic_launcher_foreground),
            CategoryViewModel("Familiares", R.drawable.ic_launcher_foreground),
            CategoryViewModel("Abecedario", R.drawable.ic_launcher_foreground),
            CategoryViewModel("Familiares", R.drawable.ic_launcher_foreground),
            CategoryViewModel("Animales", R.drawable.ic_launcher_foreground),
            CategoryViewModel("Colores", R.drawable.ic_launcher_foreground),
            CategoryViewModel("categoria8", R.drawable.ic_launcher_foreground),
            CategoryViewModel("categoria9", R.drawable.ic_launcher_foreground),
        )
    }
}


