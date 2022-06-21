package com.kodev.games.data.source

import androidx.lifecycle.LiveData
import com.kodev.games.data.source.local.entity.GameEntity

interface GameDataSource {

    fun getGames(): LiveData<Resource<List<GameEntity>>>

    fun getFavoriteGame(): LiveData<List<GameEntity>>

    fun updateGame(game: GameEntity, newState: Boolean)

}