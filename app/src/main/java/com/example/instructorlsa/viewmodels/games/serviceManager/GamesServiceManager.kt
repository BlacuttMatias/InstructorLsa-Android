package com.example.instructorlsa.viewmodels.games.serviceManager

import com.example.instructorlsa.models.Game
import com.example.instructorlsa.services.ComprehensiveGamesService
import com.example.instructorlsa.services.GamesService
import com.example.instructorlsa.viewmodels.categories.CategoryViewModel
import retrofit2.Response

interface GamesServiceManager{
    suspend fun getGames(): Response<List<Game>>
    fun getTopBarTitle(): String
}

class PracticeGamesServiceManager(category: CategoryViewModel): GamesServiceManager {
    private val service = GamesService()
    val category: CategoryViewModel

    init{
        this.category = category
    }

    override suspend fun getGames(): Response<List<Game>> {
        return service.getGames(categoryName = category.name)
    }

    override fun getTopBarTitle(): String {
        return category.name
    }
}

class ComprehensiveGamesServiceManager(): GamesServiceManager {
    private val service = ComprehensiveGamesService()

    override suspend fun getGames(): Response<List<Game>> {
        return service.getGames()
    }

    override fun getTopBarTitle(): String {
        return "Juegos Integrales"
    }
}