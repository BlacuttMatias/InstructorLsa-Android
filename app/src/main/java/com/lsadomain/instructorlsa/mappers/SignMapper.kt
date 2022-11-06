package com.lsadomain.instructorlsa.mappers

import com.lsadomain.instructorlsa.models.Sign
import com.lsadomain.instructorlsa.models.SignGame
import com.lsadomain.instructorlsa.viewmodels.signs.SignViewModel

class SignMapper {
    fun map(sign: Sign): SignViewModel{
        return SignViewModel(id = sign.id, name = sign.name, urlVideo = sign.urlVideo, isCompleted = sign.completed)
    }

    fun map(sign: SignGame): SignViewModel{
        return SignViewModel(id = sign.id, name = sign.name, urlVideo = sign.urlVideo, isCompleted = sign.completed ?: true)
    }
}