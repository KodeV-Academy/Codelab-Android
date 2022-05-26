package com.kodev.games.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameEntity(
    var id: Int,
    var name: String,
    var description: String,
    var released: String,
    var background_image: String,
    var rating: String,
    var platforms: String,
    var genres: String,
    var minimum: String,
    var recommended: String,
    var bookmarked: Boolean = false,
): Parcelable
