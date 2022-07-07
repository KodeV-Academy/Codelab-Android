package com.onedev.mycrud.api.remote

import com.onedev.mycrud.api.response.BooksResponse
import com.onedev.mycrud.api.response.Login
import com.onedev.mycrud.api.response.Register
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("v1/book/login")
    fun login(
        @Body requestLogin: Login.Request
    ): Call<Login.Response>

    @POST("v1/book/register")
    fun register(
        @Body requestRegister: Register.Request
    ): Call<Register.Response>

    @GET("v1/book")
    fun getBooks(): Call<BooksResponse>
}