package com.lsadomain.instructorlsa.services

import com.lsadomain.instructorlsa.models.Category
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap

interface CategoriesApiService{
    @GET("practice/categories")
    suspend fun getCategories(@HeaderMap headers: Map<String, String>): Response<List<Category>>
}

class CategoriesService {
    suspend fun getCategories(): Response<List<Category>> {
        return RetrofitBuilder.getRetrofitInstance()
            .create(CategoriesApiService::class.java)
            .getCategories(headers = RetrofitBuilder.getHeaders())
    }
}