package com.kodev.games.ui.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kodev.games.data.GameEntity
import com.kodev.games.data.source.GameRepository
import com.kodev.games.data.source.remote.response.ResponseGame
import com.kodev.games.utils.DataDummy

class GameViewModel(private val gameRepository: GameRepository): ViewModel() {

    fun getGames(): LiveData<ResponseGame> = gameRepository.getGames()

}