package com.example.instructorlsa.viewmodels.games

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import com.example.instructorlsa.viewmodels.signs.VideoLoaderManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GuessSignScreenViewModel(game: GameViewModel, category: CategoryViewModel, delegate: GameScreenViewModel): VideoLoaderManager() {
    val game: GameViewModel
    val category: CategoryViewModel
    var answerWasSelected by mutableStateOf(false)
    var showContinueView by mutableStateOf(false)
    var answerSelected: AnswerOptionViewModel? = null
    var delegate: GameScreenViewModel

    init{
        this.game = game
        this.category = category
        this.delegate = delegate
        showLoadingFor()
    }

    fun didTapAnswerOption(answerOption: AnswerOptionViewModel){
        answerWasSelected = true
        answerSelected = answerOption
        viewModelScope.launch{
            delay(700)
            showContinueView = true
        }
    }

    fun isTheAnswerSelected(answerOption: AnswerOptionViewModel): Boolean{
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

    fun answerWasSelectedAndIsTheCorrect(answerOption: AnswerOptionViewModel): Boolean{
        return answerWasSelected && answerOption.isCorrect
    }

    fun answerWasSelectedAndIsNotCorrect(answerOption: AnswerOptionViewModel): Boolean{
        return answerWasSelected && answerSelected == answerOption && !answerOption.isCorrect
    }

    fun showStateAnswer(answerOption: AnswerOptionViewModel): Boolean{
        return answerWasSelectedAndIsTheCorrect(answerOption) || answerWasSelectedAndIsNotCorrect(answerOption)
    }

    fun didTapContinueButton(){
        delegate.goToNextScreen(answerSelected?.isCorrect)
    }
}