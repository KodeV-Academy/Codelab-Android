package com.kodev.games.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kodev.games.data.source.local.LocalDataSource
import com.kodev.games.data.source.local.entity.GameEntity
import com.kodev.games.data.source.remote.ApiResponse
import com.kodev.games.data.source.remote.RemoteDataSource
import com.kodev.games.data.source.remote.response.DataGame
import com.kodev.games.data.source.remote.response.ResponseGame
import com.kodev.games.utils.AppExecutors
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

    override fun getGames(): LiveData<Resource<PagedList<GameEntity>>> {
        return object : NetworkBoundResource<PagedList<GameEntity>, ResponseGame>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<GameEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getLocalGames(), config).build()
            }

            override fun shouldFetch(data: PagedList<GameEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<ResponseGame>> {
                return remoteDataSource.getGames()
            }

            override fun saveCallResult(data: ResponseGame) {
                val listGame = ArrayList<GameEntity>()
                for (i in data.results) {
                    val listPlatform = ArrayList<String>()
                    val listGenre = ArrayList<String>()
                    var recommended = ""
                    var minimum = ""

                    i.platforms.map {
                        if (it.platform.name == "PC") {
                            minimum = it.requirements_en?.minimum.toString()
                            recommended = it.requirements_en?.recommended.toString()
                        }
                        listPlatform.add(it.platform.name)
                    }

                    i.genres.map {
                        listGenre.add(it.name)
                    }

                    val game = GameEntity(
                        i.id,
                        i.name,
                        i.released,
                        i.background_image,
                        i.rating.toString(),
                        replaceArrayCode(listPlatform.toString()),
                        replaceArrayCode(listGenre.toString()),
                        minimum,
                        recommended)

                    listGame.add(game)
                }
                localDataSource.insertGame(listGame)
            }
        }.asLiveData()
    }

    override fun getFavoriteGame(): LiveData<PagedList<GameEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteGame(), config).build()
    }

    override fun updateGame(game: GameEntity, newState: Boolean) =
        appExecutors.diskIO().execute { localDataSource.updateGame(game, newState) }

}