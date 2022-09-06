package com.kodev.games.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kodev.games.core.data.source.local.entity.GameEntity

@Dao
interface GamesDao {

    @Query("SELECT * FROM tb_game")
    fun getLocalGames(): LiveData<List<GameEntity>>

    @Query("SELECT * FROM tb_game where favorite = 1")
    fun getFavoriteGame(): LiveData<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(courses: List<GameEntity>)

    @Update
    fun updateGame(course: GameEntity)

}