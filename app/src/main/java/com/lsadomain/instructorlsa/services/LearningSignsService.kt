package com.lsadomain.instructorlsa.services

import com.lsadomain.instructorlsa.models.Sign
import com.lsadomain.instructorlsa.ui.common.components.extensions.uppercaseAndUnaccent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface LearningSignsApiService{
    @GET("sign/category")
    suspend fun getSigns(@HeaderMap headers: Map<String, String>, @Query("category") categoryName: String): Response<List<Sign>>
}

class LearningSignsService {
    suspend fun getLearningSigns(categoryName: String): Response<List<Sign>>{
        return RetrofitBuilder.getRetrofitInstance()
            .create(LearningSignsApiService::class.java)
            .getSigns(headers = RetrofitBuilder.getHeaders(), categoryName = categoryName.uppercaseAndUnaccent())
    }
}