package com.kodev.games.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tb_game")
data class GameEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "released")
    var released: String,

    @ColumnInfo(name = "background_image")
    var background_image: String,

    @ColumnInfo(name = "rating")
    var rating: String,

    @ColumnInfo(name = "platforms")
    var platforms: String,

    @ColumnInfo(name = "genres")
    var genres: String,

    @ColumnInfo(name = "minimum")
    var minimum: String,

    @ColumnInfo(name = "recommended")
    var recommended: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false,
): Parcelable
