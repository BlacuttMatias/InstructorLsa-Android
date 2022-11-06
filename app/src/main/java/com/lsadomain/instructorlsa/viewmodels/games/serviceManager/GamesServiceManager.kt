package com.lsadomain.instructorlsa.viewmodels.games.serviceManager

import com.lsadomain.instructorlsa.models.Game
import com.lsadomain.instructorlsa.services.ComprehensiveGamesService
import com.lsadomain.instructorlsa.services.GamesService
import com.lsadomain.instructorlsa.viewmodels.categories.CategoryViewModel
import retrofit2.Response

interface GamesServiceManager{
    suspend fun getGames(): Response<List<Game>>
    fun getTopBarTitle(): String
    fun isPractice(): Boolean
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

    override fun isPractice(): Boolean {
        return true
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

    override fun isPractice(): Boolean {
        return false
    }
}