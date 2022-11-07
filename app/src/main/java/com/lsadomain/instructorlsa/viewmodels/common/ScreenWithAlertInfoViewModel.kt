package com.lsadomain.instructorlsa.viewmodels.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

open class ScreenWithAlertInfoViewModel: ViewModel() {
    var shouldShowInfoAlert by mutableStateOf(false)

    open fun shouldShowInfoButton(): Boolean{
        return true
    }

    fun onClickInfoInfoIconButton(){
        shouldShowInfoAlert = true
    }

    open fun getBodyTextAlertInfo(): String{
        return "Info"
    }

    fun getTextAlertInfoPrimaryButton(): String{
        return "Entendido"
    }

    fun onClickAlertInfoPrimaryButton(){
        shouldShowInfoAlert = false
    }
}