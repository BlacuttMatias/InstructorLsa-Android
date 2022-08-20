package com.example.instructorlsa.viewmodels.signs

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instructorlsa.models.SignBodyPut
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

    fun didBackButtonClicked(){
        if(!getCurrentSign().isCompleted){
            viewModelScope.launch {
                val requestBody = SignBodyPut(
                    signId = getCurrentSign().id,
                    categoryName = category.name,
                    completed = true
                )
                val a = signService.updateSignState(requestBody)
                getCurrentSign().isCompleted = true
                setPreviousIndex()
            }
        }
        else{
            setPreviousIndex()
        }
    }

    fun didNextButtonClicked(){
        if(!getCurrentSign().isCompleted){
            viewModelScope.launch {
                val requestBody = SignBodyPut(
                    signId = getCurrentSign().id,
                    categoryName = category.name,
                    completed = true
                )
                signService.updateSignState(requestBody)
                getCurrentSign().isCompleted = true
                setNextIndex()
            }
        }
        else{
            setNextIndex()
        }
    }
}
