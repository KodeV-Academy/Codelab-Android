package com.kodev.games.data.source

import com.kodev.games.data.source.remote.response.ResponseGame

interface GameDataSource {

    fun getGames(): ResponseGame

}