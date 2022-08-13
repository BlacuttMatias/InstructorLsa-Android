package com.example.instructorlsa.models

import com.google.gson.annotations.SerializedName

data class ResponseLearningSigns(
    @SerializedName("category") val categoryWithSignsDto: CategoryWithSignsDto
)

data class CategoryWithSignsDto(
    @SerializedName("id") val categoryId: Int?,
    @SerializedName("name") val categoryName: String?,
    val signs: List<Sign>
)
