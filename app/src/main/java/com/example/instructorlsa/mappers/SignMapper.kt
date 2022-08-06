package com.example.instructorlsa.mappers

import com.example.instructorlsa.models.Sign
import com.example.instructorlsa.viewmodels.signs.SignViewModel

class SignMapper {
    fun map(sign: Sign): SignViewModel{
        return SignViewModel(name = sign.name, urlVideo = sign.urlVideo, isCompleted = sign.completed)
    }
}