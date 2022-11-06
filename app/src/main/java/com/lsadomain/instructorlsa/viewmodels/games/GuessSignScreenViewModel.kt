package com.lsadomain.instructorlsa.viewmodels.games

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.lsadomain.instructorlsa.viewmodels.categories.CategoryViewModel
import com.lsadomain.instructorlsa.viewmodels.games.writeTheSignScreenViewModel.FinishCountdownDelegate
import com.lsadomain.instructorlsa.viewmodels.signs.VideoLoaderManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GuessSignScreenViewModel(
    game: GameViewModel,
    category: CategoryViewModel,
    delegate: GameScreenViewModel
): VideoLoaderManager(), FinishCountdownDelegate {
    val game: GameViewModel
    val category: CategoryViewModel
    var answerWasSelected by mutableStateOf(false)
    var showContinueView by mutableStateOf(false)
    var answerSelected: AnswerOptionViewModel? = null
    var delegate: GameScreenViewModel
    var countDownViewModel: CountDownTimerViewModel

    init{
        this.game = game
        this.category = category
        this.delegate = delegate
        this.countDownViewModel = CountDownTimerViewModel(delegate = this)
        showLoadingFor{
            countDownViewModel.startCountdown()
        }
    }

    fun didTapAnswerOption(answerOption: AnswerOptionViewModel){
        answerWasSelected = true
        answerSelected = answerOption
        countDownViewModel.stopCountdown()
        viewModelScope.launch{
            delay(700)
            showContinueView = true
        }
    }

    fun correctStateText(): String{
        if(answerSelected?.isCorrect == true){
            return "¡Correcto!"
        }
        else{
            return "¡Incorrecto!"
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

    override fun didFinishCountdown() {
        var defaultOption = game.answerOptions.first()
        while (defaultOption.isCorrect){
            defaultOption = game.answerOptions.random()
        }
        didTapAnswerOption(answerOption = defaultOption)
    }
}