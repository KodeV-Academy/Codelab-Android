package com.kodev.games.core.domain.usecase

import androidx.lifecycle.LiveData
import com.kodev.games.core.domain.model.Game
import com.kodev.games.core.domain.repository.IGameRepository
import com.kodev.games.core.data.source.Resource

class GameInteractor(private val iGameRepository: IGameRepository): GameUseCase {
    override fun getGames(): LiveData<Resource<List<Game>>> {
        return iGameRepository.getGames()
    }

    override fun getFavoriteGame(): LiveData<List<Game>> {
        return iGameRepository.getFavoriteGame()
    }

    override fun updateGame(game: Game, newState: Boolean) {
        return iGameRepository.updateGame(game, newState)
    }
}