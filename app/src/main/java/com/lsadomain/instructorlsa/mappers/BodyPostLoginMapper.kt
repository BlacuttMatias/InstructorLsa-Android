package com.lsadomain.instructorlsa.mappers

import com.lsadomain.instructorlsa.models.LoginPostBody
import com.lsadomain.instructorlsa.models.User

class BodyPostLoginMapper {
    fun map(user: User): LoginPostBody{
        return LoginPostBody(
            firstName = user.firstName,
            lastName = user.lastName,
            token = user.token,
            email = user.email
        )
    }
}