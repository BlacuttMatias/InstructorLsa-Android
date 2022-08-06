package com.example.instructorlsa.services

import com.example.instructorlsa.models.ResponseLearningSigns
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LearningSignsApiService{
    @GET("instructorlsa/learning/signs")
    suspend fun getSigns(@Query("categoryName") categoryName: String): ResponseLearningSigns
}

class LearningSignsService {
    suspend fun getLearningSigns(categoryName: String): ResponseLearningSigns{
        return RetrofitBuilder.getRetrofitInstance()
            .create(LearningSignsApiService::class.java)
            .getSigns(categoryName = categoryName)
    }
}