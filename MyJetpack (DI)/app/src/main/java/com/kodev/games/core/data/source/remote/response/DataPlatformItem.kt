package com.kodev.games.core.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataPlatformItem(
    val id: Int,
    val name: String,
): Parcelable
