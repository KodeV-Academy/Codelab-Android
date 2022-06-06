package com.kodev.games.data.source

import androidx.lifecycle.LiveData
import com.kodev.games.data.source.local.entity.GameEntity
import com.kodev.games.data.source.remote.response.ResponseGame
import com.kodev.games.vo.Resource

interface GameDataSource {

    fun getGames(): LiveData<Resource<List<GameEntity>>>

    fun getFavoriteGames(): LiveData<List<GameEntity>>

    fun updateGame(game: GameEntity, newState: Boolean)

}