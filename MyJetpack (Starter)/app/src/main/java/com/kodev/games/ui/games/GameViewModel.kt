package com.kodev.games.ui.games

import androidx.lifecycle.ViewModel
import com.kodev.games.data.GameRepository

class GameViewModel(private val gameRepository: GameRepository): ViewModel() {

    fun getGames() = gameRepository.getGames()

}
