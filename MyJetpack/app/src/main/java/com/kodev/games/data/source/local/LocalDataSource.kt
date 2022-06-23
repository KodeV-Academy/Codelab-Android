package com.kodev.games.data.source.local

import com.kodev.games.data.source.local.entity.GameEntity
import com.kodev.games.data.source.local.room.GamesDao

class LocalDataSource private constructor(private val gamesDao: GamesDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(gamesDao: GamesDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(gamesDao)
    }

    fun getLocalGames() = gamesDao.getLocalGames()

    fun getFavoriteGame() = gamesDao.getFavoriteGame()

    fun insertGame(listGame: List<GameEntity>) = gamesDao.insertGames(listGame)

    fun updateGame(game: GameEntity, newState: Boolean) {
        game.favorite = newState
        gamesDao.updateGame(game)
    }

}