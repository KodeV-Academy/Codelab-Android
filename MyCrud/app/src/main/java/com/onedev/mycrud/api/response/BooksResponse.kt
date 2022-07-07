package com.onedev.mycrud.api.response

data class BooksResponse(
    val `data`: List<Data>,
    val message: String?,
    val status: Boolean?
) {
    data class Data(
        val author: String?,
        val id: String?,
        val linkImage: String?,
        val title: String?,
        val userId: String?
    )
}