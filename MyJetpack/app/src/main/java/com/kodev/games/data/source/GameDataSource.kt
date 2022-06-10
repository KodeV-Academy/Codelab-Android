package com.kodev.games.data.source

import androidx.lifecycle.LiveData
import com.kodev.games.data.source.remote.response.ResponseGame

interface GameDataSource {

    fun getGames(): LiveData<ResponseGame>

}