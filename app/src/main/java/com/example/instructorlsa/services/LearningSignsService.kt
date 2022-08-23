package com.example.instructorlsa.services

import com.example.instructorlsa.models.ResponseLearningSigns
import com.example.instructorlsa.models.Sign
import com.example.instructorlsa.viewmodels.InstructorLsaConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface LearningSignsApiService{
    @GET("sign/category")
    suspend fun getSigns(@HeaderMap headers: Map<String, String>, @Query("category") categoryName: String): List<Sign>
}

class LearningSignsService {
    suspend fun getLearningSigns(categoryName: String): List<Sign>{
        return RetrofitBuilder.getRetrofitInstance()
            .create(LearningSignsApiService::class.java)
            .getSigns(headers = RetrofitBuilder.getHeaders(), categoryName = categoryName)
    }
}