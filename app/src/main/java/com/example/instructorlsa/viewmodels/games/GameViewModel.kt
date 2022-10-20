package com.example.instructorlsa.viewmodels.games

import com.example.instructorlsa.viewmodels.signs.SignViewModel

data class GameViewModel(
    val id: Int? = null,
    val name: String,
    val description: String? = null,
    val type: GameType,
    val sign: SignViewModel,
    val answerOptions: List<AnswerOptionViewModel>,
    val position: Int? = null,
    val category: String? = null
)

data class AnswerOptionViewModel(
    val text: String,
    val isCorrect: Boolean
)

enum class GameType(val type: String){
    GuessTheSign("GUESS_THE_SIGN"),
    WriteTheSign("WRITE_THE_SIGN"),
    SignTheWord("SIGN_THE_WORD"),
    Unknown("Unknown")
}