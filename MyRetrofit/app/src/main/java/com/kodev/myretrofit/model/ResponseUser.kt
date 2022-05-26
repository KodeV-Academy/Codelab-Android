package com.kodev.myretrofit.model

data class ResponseUser(
    val `data`: List<DataUser>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)