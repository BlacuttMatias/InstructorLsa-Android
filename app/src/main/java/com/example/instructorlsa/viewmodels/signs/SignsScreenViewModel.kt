package com.example.instructorlsa.viewmodels.signs

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.instructorlsa.models.Sign
import com.example.instructorlsa.services.LearningSignsService
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.instructorlsa.mappers.SignMapper
import kotlinx.coroutines.launch

class SignsScreenViewModel(category: CategoryViewModel): ViewModel() {
    var category: CategoryViewModel
    var signs by mutableStateOf(listOf<SignViewModel>())
    var loading by mutableStateOf(true)
    val service = LearningSignsService()
    val signMapper = SignMapper()

    init {
        this.category = category
    }

    fun loadInitData(){
        viewModelScope.launch {
            val signsDto = service.getLearningSigns(categoryName = category.name)
            signs =  signsDto.map { sign -> signMapper.map(sign) }
            loading = false
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