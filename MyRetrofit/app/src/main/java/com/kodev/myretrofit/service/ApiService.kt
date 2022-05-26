package com.kodev.myretrofit.service

import com.kodev.myretrofit.model.ResponseGame
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    fun getListGame(
        @Query("key") key: String,
        @Query("page_size") pageSize: String,
    ): Call<ResponseGame>
}