package com.onedev.mycrud.api.remote

import com.onedev.mycrud.api.response.Login
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("v1/book/login")
    fun login(
        @Body requestLogin: Login.RequestLogin
    ): Call<Login.ResponseLogin>

}