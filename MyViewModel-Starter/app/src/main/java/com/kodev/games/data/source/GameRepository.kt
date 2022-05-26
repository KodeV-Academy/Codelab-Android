package com.kodev.games.data.source

import com.kodev.games.data.source.remote.RemoteDataSource
import com.kodev.games.data.source.remote.response.ResponseGame

class GameRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    GameDataSource {

    companion object {
        @Volatile
        private var instance: GameRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): GameRepository =
            instance ?: synchronized(this) {
                instance ?: GameRepository(remoteDataSource).apply {
                    instance = this
                }
            }
    }

    override fun getGames(): ResponseGame {
        val response = remoteDataSource.getGames()
        return ResponseGame(response.result)
    }
}