package com.example.instructorlsa.viewmodels.signs

import com.example.instructorlsa.viewmodels.categories.CategoryViewModel

class SignsScreenViewModel(category: CategoryViewModel, signs: List<SignViewModel>) {
    var category: CategoryViewModel
    var signs: List<SignViewModel>

    init {
        this.category = category
        this.signs = signs
    }
}

object MockDataSigns{
    val signs = listOf<SignViewModel>(
        SignViewModel(name = "Rojo", urlVideo = "https://cdn.videvo.net/videvo_files/video/free/2020-05/large_watermarked/3d_ocean_1590675653_preview.mp4", isCompleted = true),
        SignViewModel(name = "Azul", urlVideo = "https://cdn.videvo.net/videvo_files/video/free/2020-05/large_watermarked/3d_ocean_1590675653_preview.mp4", isCompleted = true),
        SignViewModel(name = "Negro", urlVideo = "https://cdn.videvo.net/videvo_files/video/free/2020-05/large_watermarked/3d_ocean_1590675653_preview.mp4", isCompleted = true),
        SignViewModel(name = "Violeta", urlVideo = "https://cdn.videvo.net/videvo_files/video/free/2020-05/large_watermarked/3d_ocean_1590675653_preview.mp4", isCompleted = false),
        SignViewModel(name = "Celeste", urlVideo = "https://cdn.videvo.net/videvo_files/video/free/2020-05/large_watermarked/3d_ocean_1590675653_preview.mp4", isCompleted = false)
    )
}