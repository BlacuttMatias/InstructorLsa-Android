package com.example.instructorlsa.mappers

import com.example.instructorlsa.models.Sign
import com.example.instructorlsa.models.SignGame
import com.example.instructorlsa.viewmodels.signs.SignViewModel

class SignMapper {
    fun map(sign: Sign): SignViewModel{
        return SignViewModel(id = sign.id, name = sign.name, urlVideo = sign.urlVideo, isCompleted = sign.completed)
    }

    fun map(sign: SignGame): SignViewModel{
        return SignViewModel(id = sign.id, name = sign.name, urlVideo = sign.urlVideo, isCompleted = sign.completed)
    }
}