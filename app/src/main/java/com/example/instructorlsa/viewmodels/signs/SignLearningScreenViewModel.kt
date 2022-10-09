package com.example.instructorlsa.viewmodels.signs

import android.content.IntentSender
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instructorlsa.models.SignBodyPost
import com.example.instructorlsa.services.SignService
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

open class VideoLoaderManager: ViewModel() {
    var videoLoading by mutableStateOf(false)
    fun showVideoLoading(){
        videoLoading = true
    }
    fun hideVideoLoading(){
        videoLoading = false
    }

    fun showLoadingFor(milliSeconds: Long = 1800, onFinished: () -> Unit = {}){
        viewModelScope.launch {
            showVideoLoading()
            delay(timeMillis = milliSeconds)
            hideVideoLoading()
            onFinished.invoke()
        }
    }
}

class SignLearningScreenViewModel(category: CategoryViewModel, signs: List<SignViewModel>, currentIndex: Int):
    VideoLoaderManager() {
    var category: CategoryViewModel
    var signs by mutableStateOf(listOf<SignViewModel>())
    var currentIndex by mutableStateOf(0)
    var screenLoading by mutableStateOf(false)
    val signService = SignService()
    var isError by mutableStateOf(false)

    init {
        this.category = category
        this.signs = signs
        this.currentIndex = currentIndex
        showLoadingFor(2000)
    }

    fun isLoading(): Boolean{
        return screenLoading || videoLoading
    }

    fun getCurrentSign(): SignViewModel{
        var defaultSign: SignViewModel = signs.last()
        if(currentIndex<0){
            defaultSign = signs.first()
        }
        return signs.getOrElse(currentIndex) { defaultSign }
    }

    fun setPreviousIndex(){
        if(currentIndex > 0){
            currentIndex--
        }
    }

    fun setNextIndex(){
        if(currentIndex < signs.size-1){
            currentIndex++
        }
    }

    private fun fetchUpdateSign(onSuccess: () -> Unit){
        viewModelScope.launch {
            try{
                val requestBody = SignBodyPost(
                    signId = getCurrentSign().id
                )
                screenLoading = true
                val response = signService.updateSignState(requestBody)
                if(response.isSuccessful){
                    getCurrentSign().isCompleted = true
                    screenLoading = false
                    onSuccess.invoke()
                }
                else{
                    showError()
                }
            }
            catch(e: Exception){
                showError()
            }
        }
    }

    fun didBackButtonClicked(){
        if(!getCurrentSign().isCompleted){
            fetchUpdateSign { setPreviousIndex() }
        }
        else{
            setPreviousIndex()
        }
        showLoadingFor()
    }

    fun didNextButtonClicked(){
        if(!getCurrentSign().isCompleted){
            fetchUpdateSign { setNextIndex() }
        }
        else{
            setNextIndex()
        }
        showLoadingFor()
    }

    fun showError(){
        isError = true
        screenLoading = false
    }

}
