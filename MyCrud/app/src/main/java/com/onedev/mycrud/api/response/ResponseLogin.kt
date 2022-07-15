package com.onedev.mycrud.api.response

data class ResponseLogin(
    val `data`: Data?
) {
    data class Data(
        val id: String?,
        val user_city: String?,
        val user_created: String?,
        val user_email: String?,
        val user_instansi: String?,
        val user_job: String?,
        val user_name: String?,
        val user_password: String?,
        val user_phone: String?,
        val user_photo: String?,
        val user_updated: String?
    )
}