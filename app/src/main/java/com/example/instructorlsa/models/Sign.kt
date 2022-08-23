package com.example.instructorlsa.models

import com.google.gson.annotations.SerializedName

data class Sign(
    val id: Int,
    @SerializedName("nombre") val name: String,
    @SerializedName("url") val urlVideo: String,
    @SerializedName("categoria") val categoryId: Int,
    @SerializedName("realizado") var completed: Boolean
)