package com.kodev.games.ui.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kodev.games.core.data.source.GameRepository
import com.kodev.games.core.data.source.local.entity.GameEntity
import com.kodev.games.core.domain.model.Game
import com.kodev.games.vo.Resource

class GameViewModel(private val gameRepository: GameRepository): ViewModel() {

    fun getGames() = gameRepository.getGames()

    fun getFavoriteGame() = gameRepository.getFavoriteGame()

    fun updateGame(game: Game, newState: Boolean) = gameRepository.updateGame(game, newState)

}