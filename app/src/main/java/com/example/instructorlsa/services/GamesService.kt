package com.example.instructorlsa.services

import com.example.instructorlsa.models.Game
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface GamesApiService{
    @GET("instructorlsa/practice/games")
    suspend fun getSigns(@HeaderMap headers: Map<String, String>, @Query("categoryName") categoryName: String): List<Game>
}

class GamesService {
    suspend fun getGames(categoryName: String): List<Game> {
        return RetrofitBuilder.getRetrofitInstance()
            .create(GamesApiService::class.java)
            .getSigns(headers = RetrofitBuilder.getHeaders(), categoryName = categoryName)
    }
}