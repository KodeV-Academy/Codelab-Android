package com.kodev.games.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseGame(
    val result: List<DataGame>
): Parcelable
