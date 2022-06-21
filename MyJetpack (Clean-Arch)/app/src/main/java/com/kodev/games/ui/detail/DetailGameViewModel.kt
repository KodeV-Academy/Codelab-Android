package com.kodev.games.ui.detail

import androidx.lifecycle.ViewModel
import com.kodev.games.core.domain.model.Game
import com.kodev.games.core.domain.usecase.GameUseCase

class DetailGameViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    fun getFavoriteGame() = gameUseCase.getFavoriteGame()
    fun updateGame(game: Game, newState: Boolean) = gameUseCase.updateGame(game, newState)
}