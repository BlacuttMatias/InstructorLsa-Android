package com.example.instructorlsa.viewmodels.games

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CountDownTimerViewModel: ViewModel() {
    var initValue = 30000
    var animatedProgressPercentage by mutableStateOf(1.0f)
    private var jobTimer: Job? = null

    fun restartTimer(){
        animatedProgressPercentage = 1.0f
    }

    fun startCountdown(){
        jobTimer = viewModelScope.launch {
            while(animatedProgressPercentage >= 0.0f){
                delay(50)
                animatedProgressPercentage -= 50/(initValue.toFloat())
                if(animatedProgressPercentage < 0.0f){
                    animatedProgressPercentage = 0.0f
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