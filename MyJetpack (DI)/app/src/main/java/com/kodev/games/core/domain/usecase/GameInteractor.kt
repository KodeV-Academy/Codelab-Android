package com.kodev.games.core.domain.usecase

import com.kodev.games.core.data.source.Resource
import com.kodev.games.core.domain.model.Game
import com.kodev.games.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val iGameRepository: IGameRepository): GameUseCase {
    override fun getGames(): Flow<Resource<List<Game>>> {
        return iGameRepository.getGames()
    }

    override fun getFavoriteGame(): Flow<List<Game>> {
        return iGameRepository.getFavoriteGame()
    }

    override fun updateGame(game: Game, newState: Boolean) {
        return iGameRepository.updateGame(game, newState)
    }
}