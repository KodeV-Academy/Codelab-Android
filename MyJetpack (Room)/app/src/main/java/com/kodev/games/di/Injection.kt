package com.kodev.games.di

import android.content.Context
import com.kodev.games.data.GameRepository
import com.kodev.games.data.source.local.LocalDataSource
import com.kodev.games.data.source.local.room.GameDatabase
import com.kodev.games.data.source.remote.RemoteDataSource
import com.kodev.games.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): GameRepository {
        val database = GameDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource()
        val localDataSource = LocalDataSource.getInstance(database.gamesDao())
        val appExecutors = AppExecutors()
        return GameRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
