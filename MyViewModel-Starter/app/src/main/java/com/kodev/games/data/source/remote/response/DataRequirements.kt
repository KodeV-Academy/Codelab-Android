package com.kodev.games.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataRequirements(
    val minimum: String? = null,
    val recommended: String? = null,
): Parcelable
