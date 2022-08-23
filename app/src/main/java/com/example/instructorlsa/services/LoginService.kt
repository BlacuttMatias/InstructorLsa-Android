package com.example.instructorlsa.services

import com.example.instructorlsa.models.LoginPostBody
import com.example.instructorlsa.models.LoginResponse
import com.example.instructorlsa.models.ResponseLearningSigns
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApiService{
    @POST("login")
    suspend fun login(@Body body: LoginPostBody): Response<LoginResponse>
}

class LoginService {
    suspend fun login(body: LoginPostBody): Response<LoginResponse> {
        return RetrofitBuilder.getRetrofitInstance()
            .create(LoginApiService::class.java)
            .login(body = body)
    }
}
