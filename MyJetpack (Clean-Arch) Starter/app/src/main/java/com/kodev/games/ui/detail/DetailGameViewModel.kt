package com.kodev.games.ui.detail

import androidx.lifecycle.ViewModel
import com.kodev.games.data.source.GameRepository
import com.kodev.games.data.source.local.entity.GameEntity

class DetailGameViewModel(private val gameRepository: GameRepository): ViewModel() {
    fun updateGame(game: GameEntity, newState: Boolean) = gameRepository.updateGame(game, newState)
}