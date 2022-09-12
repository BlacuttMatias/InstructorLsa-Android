package com.example.instructorlsa.viewmodels.games.writeTheSignScreenViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instructorlsa.ui.common.components.extensions.lowercaseAndUnaccent
import com.example.instructorlsa.ui.common.components.extensions.unaccent
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import com.example.instructorlsa.viewmodels.games.AnswerOptionViewModel
import com.example.instructorlsa.viewmodels.games.CountDownTimerViewModel
import com.example.instructorlsa.viewmodels.games.GameScreenViewModel
import com.example.instructorlsa.viewmodels.games.GameViewModel
import com.example.instructorlsa.viewmodels.signs.VideoLoaderManager
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
        showLoadingFor {
            countDownViewModel.startCountdown()
        }
    }

    private fun getCorrectAnswer(): String{
        return game.answerOptions.find { it.isCorrect }?.text ?: ""
    }

    fun gameAnsweredCorrectly(): Boolean{
        return gameAnswer.lowercaseAndUnaccent() == getCorrectAnswer().lowercaseAndUnaccent()
    }

    fun correctStateText(): String{
        return if(gameAnsweredCorrectly()){
            "Correcto"
        } else{
            "Incorrecto"
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