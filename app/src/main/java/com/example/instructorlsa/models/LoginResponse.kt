package com.example.instructorlsa.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("usuario") val email: String?,
    @SerializedName("creado") val created: Boolean,
    val token: String?
)
