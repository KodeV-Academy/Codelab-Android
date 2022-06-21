package com.kodev.games.core.di

import android.content.Context
import com.kodev.games.core.data.source.GameRepository
import com.kodev.games.core.data.source.local.LocalDataSource
import com.kodev.games.core.data.source.local.room.GameDatabase
import com.kodev.games.core.data.source.remote.RemoteDataSource
import com.kodev.games.utils.AppExecutors
import com.kodev.games.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): GameRepository {

        val database = GameDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.gameDao())
        val appExecutors = AppExecutors()

        return GameRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}