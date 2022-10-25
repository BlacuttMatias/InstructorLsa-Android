package com.example.instructorlsa.mappers

import com.example.instructorlsa.models.ResponseCheckSignVideo
import com.example.instructorlsa.viewmodels.games.signTheWord.ResultCheckVideoViewModel
import com.example.instructorlsa.viewmodels.games.signTheWord.StateResultCheckVideo

class ResultCheckVideoMapper {
    private fun map(stateResultCheckVideo: String): StateResultCheckVideo{
        return StateResultCheckVideo.values().find {
            it.state == stateResultCheckVideo
        } ?: StateResultCheckVideo.Incorrect
    }

    fun map(resultCheckSignVideo: ResponseCheckSignVideo?): ResultCheckVideoViewModel{
        if (resultCheckSignVideo != null) {
            return ResultCheckVideoViewModel(
                prediction = resultCheckSignVideo.prediction,
                result = map(resultCheckSignVideo.validation),
                message = resultCheckSignVideo.response
            )
        }
        else{
            return ResultCheckVideoViewModel(
                prediction = "",
                result = map(""),
                message = ""
            )
        }
    }
}