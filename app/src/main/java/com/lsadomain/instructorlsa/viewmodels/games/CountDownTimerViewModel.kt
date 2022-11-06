package com.lsadomain.instructorlsa.viewmodels.games

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lsadomain.instructorlsa.viewmodels.games.writeTheSignScreenViewModel.FinishCountdownDelegate
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CountDownTimerViewModel(delegate: FinishCountdownDelegate): ViewModel() {
    var initValue = 30000
    var animatedProgressPercentage by mutableStateOf(1.0f)
    private var jobTimer: Job? = null
    var delegate: FinishCountdownDelegate

    init{
        this.delegate = delegate
    }

    fun startCountdown(){
        jobTimer = viewModelScope.launch {
            while(animatedProgressPercentage >= 0.0f){
                delay(50)
                animatedProgressPercentage -= 50/(initValue.toFloat())
                if(animatedProgressPercentage <= 0.0f){
                    animatedProgressPercentage = 0.0f
                    delegate.didFinishCountdown()
                }
            }
        }
    }

    fun stopCountdown(){
        jobTimer?.cancel()
    }

    fun progressSeconds(): String{
        var progressSeconds = (animatedProgressPercentage*initValue/1000).toInt() + 1
        if (animatedProgressPercentage == 0.0f){
            progressSeconds = 0
        }
        return progressSeconds.toString()
    }
}