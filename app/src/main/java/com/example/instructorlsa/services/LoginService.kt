package com.example.instructorlsa.services

import com.example.instructorlsa.models.LoginPostBody
import com.example.instructorlsa.models.LoginResponse
import com.example.instructorlsa.models.ResponseLearningSigns
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

class LoginService {
    interface LoginApiService{
        @POST("instructorlsa/login")
        suspend fun login(@Body body: LoginPostBody): LoginResponse
    }

    class LoginService {
        suspend fun login(body: LoginPostBody): LoginResponse {
            return RetrofitBuilder.getRetrofitInstance()
                .create(LoginApiService::class.java)
                .login(body = body)
        }
    }
}