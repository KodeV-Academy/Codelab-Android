package com.kodev.games.data

import androidx.lifecycle.LiveData
import com.kodev.games.data.source.remote.RemoteDataSource
import com.kodev.games.data.source.remote.response.ResponseGame

class GameRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    GameDataSource {

    companion object {
        @Volatile
        private var instance: GameRepository? = null
        fun getInstance(remoteData: RemoteDataSource): GameRepository =
            instance ?: synchronized(this) {
                instance ?: GameRepository(remoteData).apply { instance = this }
            }
    }

    override fun getGames(): LiveData<ResponseGame> {
        return remoteDataSource.getGames()
    }
}