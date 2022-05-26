package com.kodev.games.ui.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kodev.games.data.source.GameRepository
import com.kodev.games.data.source.local.entity.GameEntity
import com.kodev.games.data.source.remote.response.ResponseGame
import com.kodev.games.vo.Resource

class GameViewModel(private val gameRepository: GameRepository): ViewModel() {

    fun getGames(): LiveData<Resource<PagedList<GameEntity>>> = gameRepository.getGames()

    fun getFavoriteGame(): LiveData<PagedList<GameEntity>> = gameRepository.getFavoriteGame()

    fun updateGame(game: GameEntity, newState: Boolean) = gameRepository.updateGame(game, newState)

}