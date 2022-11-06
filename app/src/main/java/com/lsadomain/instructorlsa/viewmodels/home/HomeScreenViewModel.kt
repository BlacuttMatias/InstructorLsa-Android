package com.lsadomain.instructorlsa.viewmodels.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lsadomain.instructorlsa.mappers.CategoryMapper
import com.lsadomain.instructorlsa.services.CategoriesService
import com.lsadomain.instructorlsa.viewmodels.InstructorLsaConfig
import com.lsadomain.instructorlsa.viewmodels.categories.CategoryViewModel
import kotlinx.coroutines.launch

class HomeScreenViewModel: ViewModel() {
    var isLoading by mutableStateOf(true)
    var isError by mutableStateOf(false)
    var isEnabledComprehensiveGamesButton by mutableStateOf(false)
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
                        isEnabledComprehensiveGamesButton = categoriesViewModel.any { it.enabled }
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