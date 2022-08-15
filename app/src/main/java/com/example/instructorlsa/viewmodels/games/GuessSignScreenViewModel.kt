package com.example.instructorlsa.viewmodels.games

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel

class GuessSignScreenViewModel(game: GameViewModel, category: CategoryViewModel) {
    val game: GameViewModel
    val category: CategoryViewModel
    var answerWasSelected by mutableStateOf(false)
    var answerSelected: AnswerOption? = null

    init{
        this.game = game
        this.category = category
    }

    fun didTapAnswerOption(answerOption: AnswerOption){
        answerWasSelected = true
        answerSelected = answerOption
    }

    fun isTheAnswerSelected(answerOption: AnswerOption): Boolean{
        return answerOption == answerSelected
    }

    fun answerWasSelectedAndIsTheCorrect(answerOption: AnswerOption): Boolean{
        return answerWasSelected && answerOption.isCorrect
    }

    fun answerWasSelectedAndIsNotCorrect(answerOption: AnswerOption): Boolean{
        return answerWasSelected && answerSelected == answerOption && !answerOption.isCorrect
    }

    fun showStateAnswer(answerOption: AnswerOption): Boolean{
        return answerWasSelectedAndIsTheCorrect(answerOption) || answerWasSelectedAndIsNotCorrect(answerOption)
    }
}