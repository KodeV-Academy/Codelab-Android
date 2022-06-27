package com.kodev.games.ui.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kodev.games.core.domain.model.Game
import com.kodev.games.core.domain.usecase.GameUseCase

class GameViewModel(private val gameUseCase: GameUseCase) : ViewModel() {
    fun getGames() = gameUseCase.getGames().asLiveData()
}