package com.onedev.mycrud.api.response

object Register {
    data class Request(
        val name: String,
        val email: String,
        val password: String,
    )

    data class Response(
        val `data`: Data,
        val message: String,
        val status: Boolean
    ) {
        data class Data(
            val email: String,
            val id: String,
            val name: String,
            val password: String
        )
    }
}