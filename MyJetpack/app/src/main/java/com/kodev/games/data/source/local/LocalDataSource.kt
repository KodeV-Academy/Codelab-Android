package com.kodev.games.data.source.local

import androidx.lifecycle.LiveData
import com.kodev.games.data.source.local.entity.GameEntity
import com.kodev.games.data.source.local.entity.GamesDao

class LocalDataSource private constructor(private val gamesDao: GamesDao) {

    companion object {
        private val INSTANCE: LocalDataSource? = null

        fun getInstance(gamesDao: GamesDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(gamesDao)
    }

    fun getLocalGame(): LiveData<List<GameEntity>> = gamesDao.getLocalGames()

    fun getFavoriteGame(): LiveData<List<GameEntity>> = gamesDao.getFavoriteGame()

    fun insertGame(listGame: List<GameEntity>) = gamesDao.insertGame(listGame)

    fun updateGame(game: GameEntity, newState: Boolean) {
        game.favorite = newState
        gamesDao.updateGames(game)
    }
}