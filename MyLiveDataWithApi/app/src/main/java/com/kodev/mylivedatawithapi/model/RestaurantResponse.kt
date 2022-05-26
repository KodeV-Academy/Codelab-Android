package com.kodev.mylivedatawithapi.model

data class RestaurantResponse(
    val error: Boolean,
    val message: String,
    val restaurant: DataRestaurant
)