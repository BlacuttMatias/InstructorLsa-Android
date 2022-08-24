package com.example.instructorlsa.viewmodels.signs

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instructorlsa.models.SignBodyPost
import com.example.instructorlsa.services.SignService
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import kotlinx.coroutines.launch

class SignLearningScreenViewModel(category: CategoryViewModel, signs: List<SignViewModel>, currentIndex: Int): ViewModel() {
    var category: CategoryViewModel
    var signs by mutableStateOf(listOf<SignViewModel>())
    var currentIndex by mutableStateOf(0)
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
                signService.updateSignState(requestBody)
                getCurrentSign().isCompleted = true
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
    }

    fun didNextButtonClicked(){
        if(!getCurrentSign().isCompleted){
            fetchUpdateSign { setNextIndex() }
        }
        else{
            setNextIndex()
        }
    }
}
