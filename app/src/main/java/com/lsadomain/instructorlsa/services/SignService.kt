package com.lsadomain.instructorlsa.services

import com.lsadomain.instructorlsa.models.ResponseSignUpdate
import com.lsadomain.instructorlsa.models.SignBodyPost
import retrofit2.Response
import retrofit2.http.*

interface SignApiService{
    @POST("sign/")
    suspend fun updateSignState(@HeaderMap headers: Map<String, String>, @Body body: SignBodyPost): Response<ResponseSignUpdate>
}

class SignService {
    suspend fun updateSignState(body: SignBodyPost): Response<ResponseSignUpdate> {
        return RetrofitBuilder.getRetrofitInstance()
            .create(SignApiService::class.java)
            .updateSignState(headers = RetrofitBuilder.getHeaders(), body = body)
    }
}