package com.kodev.games.ui.games

import androidx.lifecycle.ViewModel
import com.kodev.games.core.data.source.GameRepository
import com.kodev.games.core.domain.model.Game

class GameViewModel(private val gameRepository: GameRepository): ViewModel() {
    fun getGames() = gameRepository.getGames()
    fun getFavoriteGame() = gameRepository.getFavoriteGame()
    fun updateGame(game: Game, newState: Boolean) = gameRepository.updateGame(game, newState)
}