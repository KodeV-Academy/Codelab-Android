package com.kodev.games.ui.games

import androidx.lifecycle.ViewModel
import com.kodev.games.utils.DataDummy

class GameViewModel(): ViewModel() {

    fun getGames() = DataDummy.generateDataGames()

}