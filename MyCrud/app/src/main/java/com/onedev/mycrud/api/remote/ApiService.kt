package com.onedev.mycrud.api.remote

import com.onedev.mycrud.api.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("user/login")
    fun login(
        @Body requestLogin: RequestLogin
    ): Call<ResponseLogin>

    @GET("user/listuser")
    fun listUser(): Call<UserListResponse>

    @POST("user/createuser")
    fun addUser(
        @Body requestAddUser: RequestAddUser
    ): Call<ResponseAdduUser>

    @PATCH("user/{id}")
    fun updateUser(
        @Path("id") id: String,
        @Body requestEditUser: RequestEditUser
    ): Call<ResponseEditUser>

    @DELETE("user/{id}")
    fun deleteUser(
        @Path("id") id: String
    ): Call<ResponseDeleteUser>
}