package com.onedev.mycrud.api.response

data class ResponseCustomerList(
    val `data`: List<Data>?
) {
    data class Data(
        val city: String?,
        val id: String?,
        val job: String?,
        val name: String?,
        val phone: String?,
        val user_id: String?,
        val workplace: String?
    )
}