package com.example.instructorlsa.viewmodels.games

import com.example.instructorlsa.viewmodels.signs.SignViewModel

data class GameViewModel(
    val name: String,
    val type: GameType,
    val sign: SignViewModel,
    val answerOptions: List<AnswerOption>
)

data class AnswerOption(
    val text: String,
    val isCorrect: Boolean
)

enum class GameType(val type: String){
    GuessTheSign("GUESS_THE_SIGN"),
    WriteTheSign("WRITE_THE_SIGN"),
    SignTheWord("SIGN_THE_WORD")
}