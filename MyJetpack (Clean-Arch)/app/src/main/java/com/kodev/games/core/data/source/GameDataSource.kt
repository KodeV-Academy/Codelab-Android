package com.kodev.games.core.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.kodev.games.core.data.source.local.entity.GameEntity
import com.kodev.games.core.domain.model.Game
import com.kodev.games.vo.Resource

interface GameDataSource {

    fun getGames(): LiveData<Resource<List<Game>>>

    fun getFavoriteGame(): LiveData<List<Game>>

    fun updateGame(game: Game, newState: Boolean)

}