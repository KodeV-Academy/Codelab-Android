package com.kodev.mylivedatawithapi.model

data class DataRestaurant(
    val address: String,
    val city: String,
    val customerReviews: List<DataCustomerReview>,
    val description: String,
    val id: String,
    val name: String,
    val pictureId: String,
    val rating: Double
)