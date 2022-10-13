package com.example.instructorlsa.services

import com.example.instructorlsa.models.Game
import com.example.instructorlsa.ui.common.components.extensions.uppercaseAndUnaccent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface GamesApiService{
    @GET("practice/games_v2")
    suspend fun getSigns(@HeaderMap headers: Map<String, String>, @Query("categoryName") categoryName: String): Response<List<Game>>
}

class GamesService {
    suspend fun getGames(categoryName: String): Response<List<Game>> {
        return RetrofitBuilder.getRetrofitInstance()
            .create(GamesApiService::class.java)
            .getSigns(headers = RetrofitBuilder.getHeaders(), categoryName = categoryName.uppercaseAndUnaccent())
    }
}