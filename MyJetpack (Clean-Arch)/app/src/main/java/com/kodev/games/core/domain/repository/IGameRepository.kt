package com.kodev.games.core.domain.repository

import androidx.lifecycle.LiveData
import com.kodev.games.core.domain.model.Game
import com.kodev.games.vo.Resource

interface IGameRepository {

    fun getGames(): LiveData<Resource<List<Game>>>

    fun getFavoriteGame(): LiveData<List<Game>>

    fun updateGame(game: Game, newState: Boolean)

}