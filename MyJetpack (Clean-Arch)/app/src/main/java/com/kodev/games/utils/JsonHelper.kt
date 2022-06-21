package com.kodev.games.utils

import android.content.Context
import com.kodev.games.core.data.source.remote.response.*
import org.json.JSONException
import org.json.JSONObject

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val file = context.assets.open(fileName)
            val buffer = ByteArray(file.available())
            file.read(buffer)
            file.close()
            String(buffer)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun loadGame(): ResponseGame {
        val listGenre = ArrayList<DataGenre>()
        val listPlatform = ArrayList<DataPlatform>()
        val listGame = ArrayList<DataGame>()
        var responseGame = ResponseGame(emptyList())
        var genreId = ""
        var genreName = ""
        var platformItemId = ""
        var platformItemName = ""
        var releasedAt = ""
        var minimum = ""
        var recommended = ""

        try {
            val responseObject = JSONObject(parsingFileToString("ResponseGame.json").toString())
            val listArray = responseObject.getJSONArray("results")

            for (i in 0 until listArray.length()) {
                val game = listArray.getJSONObject(i)

                val id = game.getString("id")
                val name = game.getString("name")
                val backgroundImage = game.getString("background_image")
                val rating = game.getDouble("rating")
                val released = game.getString("released")

                val genres = game.getJSONArray("genres")
                for (g in 0 until genres.length()) {
                    val genre = genres.getJSONObject(g)
                    genreId = genre.getString("id")
                    genreName = genre.getString("name")

                    listGenre.add(DataGenre(genreId.toInt(), genreName))
                }

                val platforms = game.getJSONArray("platforms")
                for (p in 0 until platforms.length()) {
                    val platform = platforms.getJSONObject(p)

                    val platformItem = platform.getJSONObject("platform")
                    platformItemId = platformItem.getString("id")
                    platformItemName = platformItem.getString("name")
                    releasedAt = platform.getString("released_at")

                    val requirement = platform.getJSONObject("requirements_en")
                    minimum = requirement.getString("minimum")
                    recommended = requirement.getString("recommended")

                    val dataPlatformItem = DataPlatformItem(platformItemId.toInt(), platformItemName)
                    val requirementEn = DataRequirements(minimum, recommended)
                    listPlatform.add(DataPlatform(dataPlatformItem, releasedAt, requirementEn))
                }

                val dataGame = DataGame(backgroundImage, listGenre, id.toInt(), name, listPlatform, rating, released)
                listGame.add(dataGame)

                responseGame = ResponseGame(listGame)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return responseGame
    }

}