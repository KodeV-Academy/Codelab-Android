package com.kodev.games.utils

import com.kodev.games.core.data.source.local.entity.GameEntity
import com.kodev.games.core.data.source.remote.response.ResponseGame
import com.kodev.games.core.domain.model.Game

object DataMapper {
    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                id = it.id,
                name = it.name,
                released = it.released,
                background_image = it.background_image,
                rating = it.rating,
                platforms = it.platforms,
                genres = it.genres,
                minimum = it.minimum,
                recommended = it.recommended,
                favorite = it.favorite
            )
        }

    fun mapDomainToEntity(input: Game) = GameEntity(
        id = input.id,
        name = input.name,
        released = input.released,
        background_image = input.background_image,
        rating = input.rating,
        platforms = input.platforms,
        genres = input.genres,
        minimum = input.minimum,
        recommended = input.recommended,
        favorite = input.favorite
    )

    fun mapResponseToEntities(data: ResponseGame): List<GameEntity> {
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
                Support.replaceArrayCode(listPlatform.toString()),
                Support.replaceArrayCode(listGenre.toString()),
                minimum,
                recommended)

            listGame.add(game)
        }
        return listGame
    }
}