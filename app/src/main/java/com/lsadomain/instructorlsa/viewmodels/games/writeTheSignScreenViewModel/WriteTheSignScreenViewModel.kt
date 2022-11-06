package com.lsadomain.instructorlsa.viewmodels.games.writeTheSignScreenViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.lsadomain.instructorlsa.ui.common.components.extensions.lowercaseAndUnaccent
import com.lsadomain.instructorlsa.viewmodels.categories.CategoryViewModel
import com.lsadomain.instructorlsa.viewmodels.games.CountDownTimerViewModel
import com.lsadomain.instructorlsa.viewmodels.games.GameScreenViewModel
import com.lsadomain.instructorlsa.viewmodels.games.GameViewModel
import com.lsadomain.instructorlsa.viewmodels.signs.VideoLoaderManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

interface TextFieldDelegate{
    fun setTypedText(text: String)
    fun getTypedText(): String
}

interface FinishCountdownDelegate{
    fun didFinishCountdown()
}

class WriteTheSignScreenViewModel(
    game: GameViewModel,
    category: CategoryViewModel,
    delegate: GameScreenViewModel
): VideoLoaderManager(), TextFieldDelegate, FinishCountdownDelegate
{
    val game: GameViewModel
    val category: CategoryViewModel
    var gameWasCompleted by mutableStateOf(false)
    var showContinueView by mutableStateOf(false)
    var delegate: GameScreenViewModel
    var gameAnswer by mutableStateOf("")
    var countDownViewModel: CountDownTimerViewModel

    init{
        this.game = game
        this.category = category
        this.delegate = delegate
        this.countDownViewModel = CountDownTimerViewModel(delegate = this)
        delegate.hideInfoButton()
        showLoadingFor {
            countDownViewModel.startCountdown()
        }
    }

    private fun getCorrectAnswer(): String{
        return game.answerOptions.find { it.isCorrect }?.text ?: ""
    }

    fun gameAnsweredCorrectly(): Boolean{
        return gameAnswer.lowercaseAndUnaccent().trim() == getCorrectAnswer().lowercaseAndUnaccent().trim()
    }

    fun correctStateText(): String{
        return if(gameAnsweredCorrectly()){
            "¡Correcto!"
        } else{
            "¡Incorrecto!"
        }
    }

    fun didTapContinueButton(){
        delegate.goToNextScreen(gameAnsweredCorrectly())
        gameAnswer = ""
    }

    fun didTapConfirmButton(){
        gameWasCompleted = true
        countDownViewModel.stopCountdown()
        viewModelScope.launch{
            delay(700)
            showContinueView = true
        }
    }

    override fun setTypedText(text: String) {
        gameAnswer = text
    }

    override fun getTypedText(): String {
        return gameAnswer
    }

    override fun didFinishCountdown() {
        didTapConfirmButton()
    }
}