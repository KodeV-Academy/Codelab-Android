package com.kodev.games.core.data.source.local

import com.kodev.games.core.data.source.local.entity.GameEntity
import com.kodev.games.core.data.source.local.room.GamesDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val gamesDao: GamesDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(gamesDao: GamesDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(gamesDao)
    }

    fun getLocalGames(): Flow<List<GameEntity>> = gamesDao.getLocalGames()

    fun getFavoriteGame(): Flow<List<GameEntity>> = gamesDao.getFavoriteGame()

    suspend fun insertGame(listGame: List<GameEntity>) = gamesDao.insertGame(listGame)

    fun updateGame(game: GameEntity, newState: Boolean) {
        game.favorite = newState
        gamesDao.updateGame(game)
    }

}