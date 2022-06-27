package com.kodev.games.core.di

import android.content.Context
import com.kodev.games.core.data.source.GameRepository
import com.kodev.games.core.data.source.local.LocalDataSource
import com.kodev.games.core.data.source.local.room.GameDatabase
import com.kodev.games.core.data.source.remote.RemoteDataSource
import com.kodev.games.core.domain.repository.IGameRepository
import com.kodev.games.core.domain.usecase.GameInteractor
import com.kodev.games.core.domain.usecase.GameUseCase
import com.kodev.games.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IGameRepository {
        val database = GameDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource()
        val localDataSource = LocalDataSource.getInstance(database.gameDao())
        val appExecutors = AppExecutors()

        return GameRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideTourismUseCase(context: Context): GameUseCase {
        val repository = provideRepository(context)
        return GameInteractor(repository)
    }
}