package com.lsadomain.instructorlsa.viewmodels.signs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lsadomain.instructorlsa.models.SignBodyPost
import com.lsadomain.instructorlsa.services.SignService
import com.lsadomain.instructorlsa.viewmodels.InstructorLsaConfig
import com.lsadomain.instructorlsa.viewmodels.categories.CategoryViewModel
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
        fetchUpdateSignIfIsNotCompleted()
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
        else{
            currentIndex = signs.size-1
        }
        InstructorLsaConfig.indexSignToLearn = currentIndex
    }

    fun setNextIndex(){
        if(currentIndex < signs.size-1){
            currentIndex++
        }
        else{
            currentIndex = 0
        }
        InstructorLsaConfig.indexSignToLearn = currentIndex
    }

    private fun fetchUpdateSignIfIsNotCompleted(onSuccess: () -> Unit = {}){
        if(!getCurrentSign().isCompleted){
            fetchUpdateSign(onSuccess)
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
        setPreviousIndex()
        showLoadingFor()
        fetchUpdateSignIfIsNotCompleted()
    }

    fun didNextButtonClicked(){
        setNextIndex()
        showLoadingFor()
        fetchUpdateSignIfIsNotCompleted()
    }

    fun showError(){
        isError = true
        screenLoading = false
    }

}
