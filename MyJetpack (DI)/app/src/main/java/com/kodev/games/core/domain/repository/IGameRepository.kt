package com.kodev.games.core.domain.repository

import com.kodev.games.core.data.source.Resource
import com.kodev.games.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getGames(): Flow<Resource<List<Game>>>
    fun getFavoriteGame(): Flow<List<Game>>
    fun updateGame(game: Game, newState: Boolean)
}