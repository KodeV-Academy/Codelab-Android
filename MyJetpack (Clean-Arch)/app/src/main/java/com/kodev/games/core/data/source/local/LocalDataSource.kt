package com.kodev.games.core.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.kodev.games.core.data.source.local.entity.GameEntity
import com.kodev.games.core.data.source.local.room.GamesDao

class LocalDataSource private constructor(private val gamesDao: GamesDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(gamesDao: GamesDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(gamesDao)
    }

    fun getLocalGames(): LiveData<List<GameEntity>> = gamesDao.getLocalGames()

    fun getFavoriteGame(): LiveData<List<GameEntity>> = gamesDao.getFavoriteGame()

    fun insertGame(listGame: List<GameEntity>) = gamesDao.insertGame(listGame)

    fun updateGame(game: GameEntity, newState: Boolean) {
        game.favorite = newState
        gamesDao.updateGame(game)
    }

}