package com.kodev.games.ui.games

import androidx.lifecycle.ViewModel
import com.kodev.games.core.domain.model.Game
import com.kodev.games.core.domain.usecase.GameUseCase

class GameViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    fun getGames() = gameUseCase.getGames()
    fun getFavoriteGame() = gameUseCase.getFavoriteGame()
    fun updateGame(game: Game, newState: Boolean) = gameUseCase.updateGame(game, newState)
}