package com.kodev.games.core.data.source.remote.api

import com.kodev.games.core.data.source.remote.response.ResponseGame
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("games")
    suspend fun getGames(
        @Query("key") key: String,
        @Query("page_size") pageSize: String
    ): ResponseGame
}