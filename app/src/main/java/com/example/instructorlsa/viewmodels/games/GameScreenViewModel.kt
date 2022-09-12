package com.example.instructorlsa.viewmodels.games

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instructorlsa.mappers.GameMapper
import com.example.instructorlsa.services.GamesService
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import com.example.instructorlsa.viewmodels.games.signTheWord.SignTheWordGameViewModel
import com.example.instructorlsa.viewmodels.games.writeTheSignScreenViewModel.WriteTheSignScreenViewModel
import com.example.instructorlsa.viewmodels.signs.SignViewModel
import kotlinx.coroutines.launch

class GameScreenViewModel(category: CategoryViewModel): ViewModel() {
    var games: List<GameViewModel> = listOf()
    val category: CategoryViewModel
    var indexCurrentGame by mutableStateOf(0)
    var gamesAnsweredCorrect = 0
    var allGamesAreCompleted by mutableStateOf(false)
    var gamesService = GamesService()
    var gameMapper = GameMapper()
    var isLoading by mutableStateOf(true)

    init{
        this.category = category
    }

    fun loadInitData(){
        viewModelScope.launch {
            if(games.isEmpty()){
                try{
                    val gamesDto = gamesService.getGames(categoryName = category.name).body()
                    games = gamesDto?.let { gameMapper.map(it) }.orEmpty()
                    isLoading = false
                }
                catch(e: Exception){

                }
            }
            else{
                isLoading = false
            }
        }
    }

    fun getCurrentGame(): GameViewModel{
        return games.getOrElse(indexCurrentGame) { games.first() }
    }

    fun getGuessSignScreenViewModel(): GuessSignScreenViewModel{
        return GuessSignScreenViewModel(category = this.category, game = getCurrentGame(), delegate = this)
    }

    fun getWriteSignScreenViewModel(): WriteTheSignScreenViewModel{
        return WriteTheSignScreenViewModel(category = this.category, game = getCurrentGame(), delegate = this)
    }

    fun getSignWordScreenViewModel(): SignTheWordGameViewModel{
        return SignTheWordGameViewModel(category = this.category, game = getCurrentGame(), delegate = this)
    }

    fun goToNextScreen(answerWasCorrect: Boolean?){
        answerWasCorrect?.let {
            if(it){
                gamesAnsweredCorrect++
            }
        }
        if(indexCurrentGame < games.size-1){
            indexCurrentGame++
        }
        else{
            allGamesAreCompleted = true
        }
    }

    fun getResultGames(): Double{
        return gamesAnsweredCorrect.toDouble() / games.size.toDouble()
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
    AnswerOptionViewModel(text = "Rojo", isCorrect = false),
    AnswerOptionViewModel(text = "Azul", isCorrect = true),
    AnswerOptionViewModel(text = "Celeste", isCorrect = false),
    AnswerOptionViewModel(text = "Violeta", isCorrect = false)
)

val answerOptions2 = listOf(
    AnswerOptionViewModel(text = "Azul", isCorrect = false),
    AnswerOptionViewModel(text = "Celeste", isCorrect = false),
    AnswerOptionViewModel(text = "Rojo", isCorrect = true),
    AnswerOptionViewModel(text = "Violeta", isCorrect = false)
)

val mockGames = listOf(
    GameViewModel(name = "Adivina la seña", type = GameType.GuessTheSign, sign = mockSign1, answerOptions = answerOptions1),
    GameViewModel(name = "Adivina la seña", type = GameType.GuessTheSign, sign = mockSign2, answerOptions = answerOptions2)
)