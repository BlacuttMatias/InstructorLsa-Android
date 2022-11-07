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
import com.lsadomain.instructorlsa.viewmodels.common.ScreenWithAlertInfoViewModel
import kotlinx.coroutines.launch

class HomeScreenViewModel: ScreenWithAlertInfoViewModel() {
    var isLoading by mutableStateOf(true)
    var isError by mutableStateOf(false)
    var isEnabledComprehensiveGamesButton by mutableStateOf(true)
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

    override fun shouldShowInfoButton(): Boolean{
        return !isEnabledComprehensiveGamesButton
    }

    override fun getBodyTextAlertInfo(): String{
        return "Recordá que los Juegos Integrales se desbloquearán una vez que hayas completado al menos una categoría de la Sección Aprendizaje."
    }
}