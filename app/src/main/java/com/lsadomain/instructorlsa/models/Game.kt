package com.lsadomain.instructorlsa.models

data class Game(
    val id: Int?,
    val name: String,
    val type: String,
    val description: String?,
    val sign: SignGame,
    val options: List<AnswerOptionGame>,
    val position: Int?,
    val category: String?
)

data class AnswerOptionGame(
    val text: String,
    val correct: Boolean
)