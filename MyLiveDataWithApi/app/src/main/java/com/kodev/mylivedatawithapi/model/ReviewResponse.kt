package com.kodev.mylivedatawithapi.model

data class ReviewResponse(
    val error: Boolean,
    val message: String,
    val customerReviews: List<DataCustomerReview>
)