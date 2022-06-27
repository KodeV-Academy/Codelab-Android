package com.kodev.games.core.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataGenre(
    val id: Int,
    val name: String
): Parcelable
