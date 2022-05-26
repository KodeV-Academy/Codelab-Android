package com.kodev.myretrofit.service

import com.kodev.myretrofit.model.ResponseAddUser
import com.kodev.myretrofit.model.ResponseUser
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServiceReqres {
    @GET("users")
    fun getListUser(): Call<ResponseUser>

    @FormUrlEncoded
    @POST("users")
    fun addUser(
        @Field("name") name: String,
        @Field("job") job: String
    ): Call<ResponseAddUser>
}