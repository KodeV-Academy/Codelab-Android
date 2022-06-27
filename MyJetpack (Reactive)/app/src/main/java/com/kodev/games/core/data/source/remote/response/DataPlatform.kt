package com.kodev.games.core.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataPlatform(
    val platform: DataPlatformItem,
    val released_at: String,
    val requirements_en: DataRequirements? = null,
): Parcelable
