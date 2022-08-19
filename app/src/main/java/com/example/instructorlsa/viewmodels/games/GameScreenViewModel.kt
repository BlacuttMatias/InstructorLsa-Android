package com.example.instructorlsa.viewmodels.games

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import com.example.instructorlsa.viewmodels.signs.SignViewModel

class GameScreenViewModel(category: CategoryViewModel): ViewModel() {
    var games: List<GameViewModel> = listOf()
    val category: CategoryViewModel
    var indexCurrentGame by mutableStateOf(0)
    var gamesAnsweredCorrect = 0

    init{
        this.category = category
        this.games = mockGames
    }

    fun getCurrentGame(): GameViewModel{
        return games.getOrElse(indexCurrentGame) { games.first() }
    }

    fun getGuessSignScreenViewModel(): GuessSignScreenViewModel{
        return GuessSignScreenViewModel(category = this.category, game = getCurrentGame(), delegate = this)
    }

    fun allGamesAreCompleted(): Boolean{
        return indexCurrentGame >= games.size
    }

    fun goToNextScreen(answerWasCorrect: Boolean?){
        answerWasCorrect?.let {
            if(it){
                gamesAnsweredCorrect++
            }
        }
        indexCurrentGame++
    }
}

val mockSign1 = SignViewModel(
    name = "Azul",
    isCompleted = true,
    id = 1,
    urlVideo = "https://cdn.videvo.net/videvo_files/video/free/2020-05/large_watermarked/3d_ocean_1590675653_preview.mp4"
)

val mockSign2 = SignViewModel(
    name = "Rojo",
    isCompleted = true,
    id = 2,
    urlVideo = "https://cdn.videvo.net/videvo_files/video/free/2020-05/large_watermarked/3d_ocean_1590675653_preview.mp4"
)

val answerOptions1 = listOf(
    AnswerOption(text = "Rojo", isCorrect = false),
    AnswerOption(text = "Azul", isCorrect = true),
    AnswerOption(text = "Celeste", isCorrect = false),
    AnswerOption(text = "Violeta", isCorrect = false)
)

val answerOptions2 = listOf(
    AnswerOption(text = "Azul", isCorrect = false),
    AnswerOption(text = "Celeste", isCorrect = false),
    AnswerOption(text = "Rojo", isCorrect = true),
    AnswerOption(text = "Violeta", isCorrect = false)
)

val mockGames = listOf(
    GameViewModel(name = "Adivina la seña", type = GameType.GuessTheSign, sign = mockSign1, answerOptions = answerOptions1),
    GameViewModel(name = "Adivina la seña", type = GameType.GuessTheSign, sign = mockSign2, answerOptions = answerOptions2)
)