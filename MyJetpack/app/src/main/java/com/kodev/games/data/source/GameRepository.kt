package com.kodev.games.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    override fun getGames(): LiveData<ResponseGame> {
        val getGames = MutableLiveData<ResponseGame>()
        remoteDataSource.getGames(object : RemoteDataSource.LoadGetGames {
            override fun onLoadGetGames(listGame: ResponseGame) {
               getGames.value = listGame
            }
        })
        return getGames
    }

}