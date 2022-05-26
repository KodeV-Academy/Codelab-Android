package com.kodev.games.data.source.remote.api

import com.kodev.games.data.source.remote.response.ResponseGame
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("games")
    fun getGames(
        @Query("key") key: String,
        @Query("page_size") pageSize: String
    ): Call<ResponseGame>
}