package com.kodev.games.data.source

import androidx.lifecycle.LiveData
import com.kodev.games.data.source.local.LocalDataSource
import com.kodev.games.data.source.local.entity.GameEntity
import com.kodev.games.data.source.remote.ApiResponse
import com.kodev.games.data.source.remote.RemoteDataSource
import com.kodev.games.data.source.remote.response.DataGame
import com.kodev.games.data.source.remote.response.ResponseGame
import com.kodev.games.utils.AppExecutors
import com.kodev.games.utils.DataMapper.mapResponseToEntity
import com.kodev.games.utils.Support.replaceArrayCode
import com.kodev.games.vo.Resource

class GameRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    GameDataSource {

    companion object {
        @Volatile
        private var instance: GameRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): GameRepository =
            instance ?: synchronized(this) {
                instance ?: GameRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
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
                val listGame = mapResponseToEntity(data)
                localDataSource.insertGame(listGame)
            }
        }.asLiveData()
    }

    override fun getFavoriteGame(): LiveData<List<GameEntity>> =
        localDataSource.getFavoriteGame()

    override fun updateGame(game: GameEntity, newState: Boolean) =
        appExecutors.diskIO().execute { localDataSource.updateGame(game, newState) }

}