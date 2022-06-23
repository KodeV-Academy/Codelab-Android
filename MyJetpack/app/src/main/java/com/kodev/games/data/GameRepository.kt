package com.kodev.games.data

import androidx.lifecycle.LiveData
import com.kodev.games.data.source.NetworkBoundResource
import com.kodev.games.data.source.Resource
import com.kodev.games.data.source.local.LocalDataSource
import com.kodev.games.data.source.local.entity.GameEntity
import com.kodev.games.data.source.remote.ApiResponse
import com.kodev.games.data.source.remote.RemoteDataSource
import com.kodev.games.data.source.remote.response.ResponseGame
import com.kodev.games.utils.AppExecutors
import com.kodev.games.utils.DataMapper

class GameRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    GameDataSource {

    companion object {
        @Volatile
        private var instance: GameRepository? = null
        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): GameRepository =
            instance ?: synchronized(this) {
                instance ?: GameRepository(
                    remoteDataSource,
                    localDataSource,
                    appExecutors
                ).apply { instance = this }
            }
    }

    override fun getGames(): LiveData<Resource<List<GameEntity>>> {
        return object : NetworkBoundResource<List<GameEntity>, ResponseGame>(appExecutors) {
            override fun loadFromDB(): LiveData<List<GameEntity>> {
                return localDataSource.getLocalGames()
            }

            override fun shouldFetch(data: List<GameEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<ResponseGame>> {
                return remoteDataSource.getGames()
            }

            override fun saveCallResult(data: ResponseGame) {
                val listGame = DataMapper.mapResponseToEntity(data)
                return localDataSource.insertGame(listGame)
            }
        }.asLiveData()
    }

    override fun getFavoriteGame(): LiveData<List<GameEntity>> {
        return localDataSource.getFavoriteGame()
    }

    override fun updateGame(game: GameEntity, newState: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.updateGame(game, newState)
        }
    }
}