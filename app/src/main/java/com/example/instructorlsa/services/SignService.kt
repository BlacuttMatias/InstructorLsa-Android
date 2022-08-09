package com.example.instructorlsa.services

import com.example.instructorlsa.models.ResponseLearningSigns
import com.example.instructorlsa.models.ResponseSignUpdate
import com.example.instructorlsa.models.SignBodyPut
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface SignApiService{
    @PUT("instructorlsa/learning/sign")
    suspend fun updateSignState(@Body body: SignBodyPut): ResponseSignUpdate
}

class SignService {
    suspend fun updateSignState(body: SignBodyPut): ResponseSignUpdate {
        return RetrofitBuilder.getRetrofitInstance()
            .create(SignApiService::class.java)
            .updateSignState(body = body)
    }
}