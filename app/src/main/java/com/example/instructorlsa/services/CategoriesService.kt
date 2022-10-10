package com.example.instructorlsa.services

import com.example.instructorlsa.models.Category
import com.example.instructorlsa.models.Sign
import com.example.instructorlsa.ui.common.components.extensions.uppercaseAndUnaccent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface CategoriesApiService{
    @GET("practice/categories")
    suspend fun getCategories(@HeaderMap headers: Map<String, String>): Response<List<Category>>
}

class CategoriesService {
    suspend fun getCategories(): Response<List<Category>> {
        return RetrofitBuilder.getRetrofitMockInstance()
            .create(CategoriesApiService::class.java)
            .getCategories(headers = RetrofitBuilder.getHeaders())
    }
}