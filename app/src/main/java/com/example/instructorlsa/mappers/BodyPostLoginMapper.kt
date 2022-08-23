package com.example.instructorlsa.mappers

import com.example.instructorlsa.models.LoginPostBody
import com.example.instructorlsa.models.User

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