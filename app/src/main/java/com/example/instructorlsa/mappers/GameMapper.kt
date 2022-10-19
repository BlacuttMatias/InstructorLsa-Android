package com.example.instructorlsa.mappers

import com.example.instructorlsa.models.Game
import com.example.instructorlsa.viewmodels.games.GameType
import com.example.instructorlsa.viewmodels.games.GameViewModel

class GameMapper {
    val signMapper = SignMapper()
    val answerOptionMapper = AnswerOptionMapper()

    private fun mapGameType(gameType: String): GameType{
        return GameType.values().find {
            it.type == gameType
        } ?: GameType.Unknown
    }

    fun map(game: Game): GameViewModel {
        return GameViewModel(
            id = game.id,
            name = game.name,
            description = game.description,
            type = mapGameType(game.type),
            sign = signMapper.map(game.sign),
            answerOptions = answerOptionMapper.map(game.options),
            position = game.position,
            category = game.category
        )
    }

    fun map(games: List<Game>): List<GameViewModel> {
        return games.map {
            map(game = it)
        }
    }
}