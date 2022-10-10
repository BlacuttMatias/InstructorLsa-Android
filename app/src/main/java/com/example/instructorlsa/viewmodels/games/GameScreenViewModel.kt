package com.example.instructorlsa.viewmodels.games

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instructorlsa.mappers.GameMapper
import com.example.instructorlsa.services.GamesService
import com.example.instructorlsa.viewmodels.InstructorLsaConfig
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import com.example.instructorlsa.viewmodels.games.signTheWord.SignTheWordGameViewModel
import com.example.instructorlsa.viewmodels.games.writeTheSignScreenViewModel.WriteTheSignScreenViewModel
import com.example.instructorlsa.viewmodels.signs.SignViewModel
import kotlinx.coroutines.launch

class GameScreenViewModel(
    category: CategoryViewModel,
    games: List<GameViewModel> = listOf(),
    indexCurrentGame: Int = 0,
    gamesAnsweredCorrect: Int = 0
): ViewModel() {
    var games: List<GameViewModel> = listOf()
    val category: CategoryViewModel
    var indexCurrentGame by mutableStateOf(0)
    var currentGameType by mutableStateOf(GameType.Unknown)
    var gamesAnsweredCorrect = 0
    var allGamesAreCompleted by mutableStateOf(false)
    var gamesService = GamesService()
    var gameMapper = GameMapper()
    var shouldShowBackAlertDialog by mutableStateOf(false)
    var isLoading by mutableStateOf(true)
    var isError by mutableStateOf(false)

    init{
        this.category = category
        this.games = games
        this.indexCurrentGame = indexCurrentGame
        this.gamesAnsweredCorrect = gamesAnsweredCorrect
        if(games.isNotEmpty()){
            isLoading = false
        }
    }

    fun loadInitData(){
        viewModelScope.launch {
            if(games.isEmpty()){
                try{
                    val response = gamesService.getGames(categoryName = category.name)
                    if(response.isSuccessful){
                        val gamesDto = response.body()
                        games = gamesDto?.let { gameMapper.map(it) }.orEmpty()
                        isLoading = false
                    }
                    else{
                        showError()
                    }
                }
                catch(e: Exception){
                    showError()
                }
            }
            else{
                isLoading = false
            }
        }
    }

    fun shouldShowInfoButton(): Boolean{
        return currentGameType == GameType.SignTheWord
    }

    fun getCurrentGame(): GameViewModel{
        val currentGame = games.getOrElse(indexCurrentGame) {
            GameViewModel(name = "",
                type = GameType.Unknown,
                sign = mockSign1,
                answerOptions = answerOptions1
            )
        }
        currentGameType = currentGame.type
        return currentGame
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
        shouldShowBackAlertDialog = false
        answerWasCorrect?.let {
            if(it){
                gamesAnsweredCorrect++
            }
        }
        if(indexCurrentGame < games.size-1){
            indexCurrentGame++
            InstructorLsaConfig.currentGames = games
            InstructorLsaConfig.indexCurrentGame = indexCurrentGame
            InstructorLsaConfig.gamesAnsweredCorrect = gamesAnsweredCorrect
        }
        else{
            allGamesAreCompleted = true
        }
    }

    fun onBackButtonPressed() {
        shouldShowBackAlertDialog = true
    }

    fun getResultGames(): Double{
        return gamesAnsweredCorrect.toDouble() / games.size.toDouble()
    }

    fun getDialogBodyText(): String{
        return "Al abandonar la práctica, perderás todo el progreso obtenido"
    }

    fun onAlertDialogCancelButtonPressed() {
        shouldShowBackAlertDialog = false
    }

    fun showError(){
        isError = true
        isLoading = false
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