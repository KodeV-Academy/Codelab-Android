package com.moon.myapplication.ui.food

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodData(
    val image: String,
    val name: String,
    val description: String,
): Parcelable