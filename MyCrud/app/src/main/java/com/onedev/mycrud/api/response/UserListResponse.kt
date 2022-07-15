package com.onedev.mycrud.api.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class UserListResponse(
    val `data`: List<Data>
) {
    @Parcelize
    data class Data(
        val id: String?,
        val user_created: String?,
        val user_email: String?,
        val user_holding: String?,
        val user_name: String?,
        val user_password: String?,
        val user_phone: String?,
        val user_photo: String?,
        val user_updated: String?
    ): Parcelable
}