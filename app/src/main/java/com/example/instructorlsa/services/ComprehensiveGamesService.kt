package com.example.instructorlsa.services

import com.example.instructorlsa.models.Game
import com.example.instructorlsa.ui.common.components.extensions.uppercaseAndUnaccent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface ComprehensiveGamesApiService{
    @GET("practice/games_v2")
    suspend fun getGames(@HeaderMap headers: Map<String, String>): Response<List<Game>>
}

class ComprehensiveGamesService {
    suspend fun getGames(): Response<List<Game>> {
        return RetrofitBuilder.getRetrofitInstance()
            .create(ComprehensiveGamesApiService::class.java)
            .getGames(headers = RetrofitBuilder.getHeaders())
    }
}