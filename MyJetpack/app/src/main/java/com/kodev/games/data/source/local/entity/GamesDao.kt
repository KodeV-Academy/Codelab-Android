package com.kodev.games.data.source.local.entity

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GamesDao {

    @Query("SELECT * FROM tb_game")
    fun getLocalGames(): LiveData<List<GameEntity>>

    @Query("SELECT * FROM tb_game WHERE favorite = 1")
    fun getFavoriteGame(): LiveData<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: List<GameEntity>)

    @Update
    fun updateGames(games: GameEntity)

}