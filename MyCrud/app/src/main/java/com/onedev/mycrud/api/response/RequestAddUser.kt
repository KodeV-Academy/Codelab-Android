package com.onedev.mycrud.api.response

data class RequestAddUser(
    val user_email: String?,
    val user_holding: String?,
    val user_name: String?,
    val user_password: String?,
    val user_phone: String?,
    val user_photo: String?
)