package com.lsadomain.instructorlsa.services

import com.lsadomain.instructorlsa.models.Game
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap

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