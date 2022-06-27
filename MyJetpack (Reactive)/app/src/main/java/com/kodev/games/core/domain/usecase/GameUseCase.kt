package com.kodev.games.core.domain.usecase

import com.kodev.games.core.data.source.Resource
import com.kodev.games.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getGames(): Flow<Resource<List<Game>>>
    fun getFavoriteGame(): Flow<List<Game>>
    fun updateGame(game: Game, newState: Boolean)
}