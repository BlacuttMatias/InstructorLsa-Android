package com.example.instructorlsa.viewmodels.games

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GuessSignScreenViewModel(game: GameViewModel, category: CategoryViewModel, delegate: GameScreenViewModel): ViewModel() {
    val game: GameViewModel
    val category: CategoryViewModel
    var answerWasSelected by mutableStateOf(false)
    var showContinueView by mutableStateOf(false)
    var answerSelected: AnswerOption? = null
    var delegate: GameScreenViewModel

    init{
        this.game = game
        this.category = category
        this.delegate = delegate
    }

    fun didTapAnswerOption(answerOption: AnswerOption){
        answerWasSelected = true
        answerSelected = answerOption
        viewModelScope.launch{
            delay(700)
            showContinueView = true
        }
    }

    fun isTheAnswerSelected(answerOption: AnswerOption): Boolean{
        return answerOption == answerSelected
    }

    fun correctStateText(): String{
        if(answerSelected?.isCorrect == true){
            return "Correcto"
        }
        else{
            return "Incorrecto"
        }
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

    fun didTapContinueButton(){
        delegate.goToNextScreen(answerSelected?.isCorrect)
    }
}