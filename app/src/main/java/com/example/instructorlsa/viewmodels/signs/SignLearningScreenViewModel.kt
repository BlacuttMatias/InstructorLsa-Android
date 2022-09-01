package com.example.instructorlsa.viewmodels.signs

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

    fun showLoadingFor(milliSeconds: Long){
        viewModelScope.launch {
            showVideoLoading()
            delay(timeMillis = milliSeconds)
            hideVideoLoading()
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

    init {
        this.category = category
        this.signs = signs
        this.currentIndex = currentIndex
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
                signService.updateSignState(requestBody)
                getCurrentSign().isCompleted = true
                screenLoading = false
                onSuccess.invoke()
            }
            catch(e: Exception){

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
        showLoadingFor(1500)
    }

    fun didNextButtonClicked(){
        if(!getCurrentSign().isCompleted){
            fetchUpdateSign { setNextIndex() }
        }
        else{
            setNextIndex()
        }
        showLoadingFor(1500)
    }



}
