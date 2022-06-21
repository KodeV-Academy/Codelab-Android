package com.kodev.games.core.domain.usecase

import androidx.lifecycle.LiveData
import com.kodev.games.core.domain.model.Game
import com.kodev.games.core.data.source.Resource

interface GameUseCase {
    fun getGames(): LiveData<Resource<List<Game>>>
    fun getFavoriteGame(): LiveData<List<Game>>
    fun updateGame(game: Game, newState: Boolean)
}