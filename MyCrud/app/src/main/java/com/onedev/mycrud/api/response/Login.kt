package com.onedev.mycrud.api.response

object Login {
    data class RequestLogin(
        val email: String,
        val password: String,
    )

    data class ResponseLogin(
        val `data`: DataLogin,
        val message: String,
        val status: Boolean
    ) {
        data class DataLogin(
            val email: String,
            val id: String,
            val name: String,
            val password: String
        )
    }
}