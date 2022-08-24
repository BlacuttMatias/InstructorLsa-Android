package com.example.instructorlsa.models

data class LoginPostBody(
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val token: String?
)