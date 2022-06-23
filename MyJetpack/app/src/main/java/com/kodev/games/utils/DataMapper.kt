package com.kodev.games.utils

import com.kodev.games.data.source.local.entity.GameEntity
import com.kodev.games.data.source.remote.response.ResponseGame
import kotlin.math.min

object DataMapper {

    fun mapResponseToEntity(data: ResponseGame): List<GameEntity> {
        val listGame = ArrayList<GameEntity>()

        for (i in data.results) {
            val listPlatform = ArrayList<String>()
            val listGenre = ArrayList<String>()
            var recommended = ""
            var minimum = ""

            i.platforms.map {
                if (it.platform.name == "PC") {
                    minimum = it.requirements_en?.minimum.toString()
                    recommended = it.requirements_en?.recommended.toString()
                }
                listPlatform.add(it.platform.name)
            }

            i.genres.map {
                listGenre.add(it.name)
            }

            val game = GameEntity(
                i.id,
                i.name,
                i.released,
                i.background_image,
                i.rating.toString(),
                listPlatform.toString(),
                listGenre.toString(),
                minimum,
                recommended
            )

            listGame.add(game)
        }
        return listGame
    }
}