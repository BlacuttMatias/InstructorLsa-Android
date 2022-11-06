package com.lsadomain.instructorlsa.viewmodels.signs

import androidx.lifecycle.ViewModel
import com.lsadomain.instructorlsa.services.LearningSignsService
import com.lsadomain.instructorlsa.viewmodels.categories.CategoryViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.lsadomain.instructorlsa.mappers.SignMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignsScreenViewModel(category: CategoryViewModel): ViewModel() {
    var category: CategoryViewModel
    var signs by mutableStateOf(listOf<SignViewModel>())
    var loading by mutableStateOf(true)
    var isError by mutableStateOf(false)
    val service = LearningSignsService()
    val signMapper = SignMapper()

    init {
        this.category = category
    }

    fun loadInitData(){
        viewModelScope.launch {
            try{
                val response = service.getLearningSigns(categoryName = category.name)
                if(response.isSuccessful){
                    val signsDto = response.body()
                    if (signsDto != null) {
                        signs =  signsDto.map { sign -> signMapper.map(sign) }
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
                delay(timeMillis = 1000)
                loading = false
            }
        }
    }
}

object MockDataSigns{
    val signs = listOf<SignViewModel>(
        SignViewModel(id = 1, name = "Rojo", urlVideo = "https://cdn.videvo.net/videvo_files/video/free/2020-05/large_watermarked/3d_ocean_1590675653_preview.mp4", isCompleted = true),
        SignViewModel(id = 2, name = "Azul", urlVideo = "https://cdn.videvo.net/videvo_files/video/free/2020-05/large_watermarked/3d_ocean_1590675653_preview.mp4", isCompleted = true),
        SignViewModel(id = 3, name = "Negro", urlVideo = "https://cdn.videvo.net/videvo_files/video/free/2020-05/large_watermarked/3d_ocean_1590675653_preview.mp4", isCompleted = true),
        SignViewModel(id = 4, name = "Violeta", urlVideo = "https://cdn.videvo.net/videvo_files/video/free/2020-05/large_watermarked/3d_ocean_1590675653_preview.mp4", isCompleted = false),
        SignViewModel(id = 5, name = "Celeste", urlVideo = "https://cdn.videvo.net/videvo_files/video/free/2020-05/large_watermarked/3d_ocean_1590675653_preview.mp4", isCompleted = false)
    )
}