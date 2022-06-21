package com.kodev.games.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Game(
    var id: Int,
    var name: String,
    var released: String,
    var background_image: String,
    var rating: String,
    var platforms: String,
    var genres: String,
    var minimum: String,
    var recommended: String,
    var favorite: Boolean = false,
): Parcelable