package com.example.instructorlsa.mappers

import com.example.instructorlsa.constants.Constants
import com.example.instructorlsa.models.Category
import com.example.instructorlsa.ui.common.components.extensions.lowercaseAndUnaccent
import com.example.instructorlsa.viewmodels.categories.CategoryType
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import com.example.instructorlsa.viewmodels.games.GameType

class CategoryMapper {
    fun map(category: Category): CategoryViewModel{
        var categoryType = CategoryType.values().find {
            it.categoryName.lowercaseAndUnaccent() == category.name.lowercaseAndUnaccent()
        } ?: CategoryType.Unknown
        val categoryViewModel = Constants.allBaseCategories.find {
            it.type == categoryType
        } ?: CategoryViewModel(-1, categoryType.categoryName, categoryType, 0, false)
        return categoryViewModel.copy(id = category.id, enabled = category.enabled)
    }

    fun map(categories: List<Category>): List<CategoryViewModel>{
        return categories.map { map(it) }
    }
}