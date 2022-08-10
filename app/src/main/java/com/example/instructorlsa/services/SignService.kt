package com.example.instructorlsa.services

import com.example.instructorlsa.models.ResponseLearningSigns
import com.example.instructorlsa.models.ResponseSignUpdate
import com.example.instructorlsa.models.SignBodyPut
import com.example.instructorlsa.viewmodels.InstructorLsaConfig
import retrofit2.http.*

interface SignApiService{
    @PUT("instructorlsa/learning/sign")
    suspend fun updateSignState(@HeaderMap headers: Map<String, String>, @Body body: SignBodyPut): ResponseSignUpdate
}

class SignService {
    suspend fun updateSignState(body: SignBodyPut): ResponseSignUpdate {
        return RetrofitBuilder.getRetrofitInstance()
            .create(SignApiService::class.java)
            .updateSignState(headers = RetrofitBuilder.getHeaders(), body = body)
    }
}