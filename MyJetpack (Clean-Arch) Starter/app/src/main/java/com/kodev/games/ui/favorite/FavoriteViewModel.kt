package com.kodev.games.ui.favorite

import androidx.lifecycle.ViewModel
import com.kodev.games.data.source.GameRepository
import com.kodev.games.data.source.local.entity.GameEntity

class FavoriteViewModel(private val gameRepository: GameRepository): ViewModel() {
    fun getFavoriteGame() = gameRepository.getFavoriteGame()
    fun updateGame(game: GameEntity, newState: Boolean) = gameRepository.updateGame(game, newState)
}