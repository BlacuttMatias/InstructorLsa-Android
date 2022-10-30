package com.example.instructorlsa.viewmodels.games.signTheWord

data class ResultCheckVideoViewModel(
    val prediction: String?,
    val result: StateResultCheckVideo,
    val message: String
){
    fun isCorrect(): Boolean{
        return result == StateResultCheckVideo.Correct
    }

    fun hasToRetry(): Boolean{
        return result == StateResultCheckVideo.Retry
    }
}

enum class StateResultCheckVideo(val state: String){
    Correct("CORRECTA"),
    Incorrect("INCORECTA"),
    Retry("REINTENTAR")
}