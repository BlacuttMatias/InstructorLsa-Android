package com.example.instructorlsa.viewmodels.signs

data class SignViewModel(
    val id: Int,
    val name: String,
    val urlVideo: String,
    var isCompleted: Boolean
)
