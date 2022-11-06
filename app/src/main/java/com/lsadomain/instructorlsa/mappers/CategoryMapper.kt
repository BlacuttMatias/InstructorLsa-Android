package com.lsadomain.instructorlsa.mappers

import com.lsadomain.instructorlsa.constants.Constants
import com.lsadomain.instructorlsa.models.Category
import com.lsadomain.instructorlsa.ui.common.components.extensions.lowercaseAndUnaccent
import com.lsadomain.instructorlsa.viewmodels.categories.CategoryType
import com.lsadomain.instructorlsa.viewmodels.categories.CategoryViewModel

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