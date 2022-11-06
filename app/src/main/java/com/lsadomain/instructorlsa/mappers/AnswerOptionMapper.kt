package com.lsadomain.instructorlsa.mappers

import com.lsadomain.instructorlsa.models.AnswerOptionGame
import com.lsadomain.instructorlsa.viewmodels.games.AnswerOptionViewModel

class AnswerOptionMapper {
    fun map(answerOption: AnswerOptionGame): AnswerOptionViewModel {
        return AnswerOptionViewModel(
            text = answerOption.text,
            isCorrect = answerOption.correct
        )
    }

    fun map(answerOptions: List<AnswerOptionGame>): List<AnswerOptionViewModel> {
        return answerOptions.map {
            map(answerOption = it)
        }
    }
}