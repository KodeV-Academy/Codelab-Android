package com.kodev.mylivedatawithapi.service

import com.kodev.mylivedatawithapi.model.RestaurantResponse
import com.kodev.mylivedatawithapi.model.ReviewResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("detail/{id}")
    fun getRestaurant(
        @Path("id") id: String
    ): Call<RestaurantResponse>

    @FormUrlEncoded
    @POST("review")
    fun postReview(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("review") review: String,
    ): Call<ReviewResponse>
}