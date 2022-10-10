package com.example.instructorlsa.viewmodels.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instructorlsa.mappers.CategoryMapper
import com.example.instructorlsa.services.CategoriesService
import com.example.instructorlsa.viewmodels.InstructorLsaConfig
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import kotlinx.coroutines.launch

class HomeScreenViewModel: ViewModel() {
    var isLoading by mutableStateOf(true)
    var isError by mutableStateOf(false)
    var categoriesService = CategoriesService()
    var categoryMapper = CategoryMapper()
    var categoriesViewModel: List<CategoryViewModel> = listOf()

    fun loadCategories(){
        viewModelScope.launch {
            try{
                val response = categoriesService.getCategories()
                if(response.isSuccessful){
                    val categoriesDto = response.body()
                    if (categoriesDto != null) {
                        categoriesViewModel =  categoryMapper.map(categoriesDto)
                        InstructorLsaConfig.categoriesViewModel = categoriesViewModel
                    }
                }
                else {
                    isError = true
                }
            }
            catch(e: Exception){
                isError = true
            }
            finally {
                isLoading = false
            }
        }
    }
}