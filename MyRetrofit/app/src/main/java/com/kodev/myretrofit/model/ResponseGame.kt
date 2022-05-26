package com.kodev.myretrofit.model

data class ResponseGame(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<DataGame>
)