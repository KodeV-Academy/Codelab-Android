package com.kodev.games.core.data.source.local.room

import androidx.room.*
import com.kodev.games.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {

    @Query("SELECT * FROM tb_game")
    fun getLocalGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM tb_game where favorite = 1")
    fun getFavoriteGame(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(courses: List<GameEntity>)

    @Update
    fun updateGame(course: GameEntity)

}