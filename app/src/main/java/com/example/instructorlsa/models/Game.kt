package com.example.instructorlsa.models

data class Game(
    val id: Int?,
    val name: String,
    val type: String,
    val description: String?,
    val sign: SignGame,
    val options: List<AnswerOptionGame>
)

data class AnswerOptionGame(
    val text: String,
    val correct: Boolean
)