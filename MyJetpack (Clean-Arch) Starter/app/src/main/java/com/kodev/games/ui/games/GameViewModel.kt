package com.kodev.games.ui.games

import androidx.lifecycle.ViewModel
import com.kodev.games.data.source.GameRepository
import com.kodev.games.data.source.local.entity.GameEntity

class GameViewModel(private val gameRepository: GameRepository): ViewModel() {
    fun getGames() = gameRepository.getGames()
}