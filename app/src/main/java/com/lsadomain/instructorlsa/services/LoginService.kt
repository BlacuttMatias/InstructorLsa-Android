package com.lsadomain.instructorlsa.services

import com.lsadomain.instructorlsa.models.LoginPostBody
import com.lsadomain.instructorlsa.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService{
    @POST("login/")
    suspend fun login(@Body body: LoginPostBody): Response<LoginResponse>
}

class LoginService {
    suspend fun login(body: LoginPostBody): Response<LoginResponse> {
        return RetrofitBuilder.getRetrofitInstance()
            .create(LoginApiService::class.java)
            .login(body = body)
    }
}
