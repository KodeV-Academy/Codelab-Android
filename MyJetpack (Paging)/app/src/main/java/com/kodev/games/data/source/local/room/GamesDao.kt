package com.kodev.games.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.kodev.games.data.source.local.entity.GameEntity

@Dao
interface GamesDao {

    @Query("SELECT * FROM tb_game")
    fun getLocalGames(): DataSource.Factory<Int, GameEntity>

    @Query("SELECT * FROM tb_game where favorite = 1")
    fun getFavoriteGame(): DataSource.Factory<Int, GameEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(courses: List<GameEntity>)

    @Update
    fun updateGame(course: GameEntity)

}