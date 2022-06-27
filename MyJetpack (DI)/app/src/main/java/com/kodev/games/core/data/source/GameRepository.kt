package com.kodev.games.core.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kodev.games.core.data.source.local.LocalDataSource
import com.kodev.games.core.data.source.remote.ApiResponse
import com.kodev.games.core.data.source.remote.RemoteDataSource
import com.kodev.games.core.data.source.remote.response.ResponseGame
import com.kodev.games.core.domain.model.Game
import com.kodev.games.core.domain.repository.IGameRepository
import com.kodev.games.utils.AppExecutors
import com.kodev.games.utils.DataMapper
import com.kodev.games.utils.DataMapper.mapResponseToEntities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    IGameRepository {

    companion object {
        @Volatile
        private var instance: GameRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): GameRepository =
            instance ?: synchronized(this) {
                instance ?: GameRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getGames(): Flow<Resource<List<Game>>> {
        return object : NetworkBoundResource<List<Game>, ResponseGame>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getLocalGames().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<ResponseGame>> {
                return remoteDataSource.getGames()
            }

            override suspend fun saveCallResult(data: ResponseGame) {
                val listGame = mapResponseToEntities(data)
                localDataSource.insertGame(listGame)
            }
        }.asFlow()
    }

    override fun getFavoriteGame(): Flow<List<Game>> {
        return localDataSource.getFavoriteGame().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun updateGame(game: Game, newState: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute {
            localDataSource.updateGame(tourismEntity, newState)
        }
    }

}