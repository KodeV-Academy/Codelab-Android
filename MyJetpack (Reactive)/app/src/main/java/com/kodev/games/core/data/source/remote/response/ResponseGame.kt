package com.kodev.games.core.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseGame(
    val results: List<DataGame>
): Parcelable
