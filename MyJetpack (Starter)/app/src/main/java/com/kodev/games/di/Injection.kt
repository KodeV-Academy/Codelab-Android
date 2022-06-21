package com.kodev.games.di

import android.content.Context
import com.kodev.games.data.GameRepository
import com.kodev.games.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): GameRepository {

        val remoteDataSource = RemoteDataSource()
        return GameRepository.getInstance(remoteDataSource)
    }
}
