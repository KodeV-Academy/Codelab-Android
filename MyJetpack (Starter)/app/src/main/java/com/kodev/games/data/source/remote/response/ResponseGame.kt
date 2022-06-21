package com.kodev.games.data.source.remote.response

import android.os.Parcelable
import com.kodev.games.data.source.remote.response.DataGame
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseGame(
    val results: List<DataGame>
): Parcelable
