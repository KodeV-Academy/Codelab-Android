package com.kodev.myretrofit.model

data class DataGame(
    val background_image: String,
    val id: Int,
    val name: String,
    val rating: Double,
    val rating_top: Int,
    val ratings_count: Int,
    val released: String,
    val genres: List<DataGenre>,
    val platforms: List<DataPlatformItem>
)