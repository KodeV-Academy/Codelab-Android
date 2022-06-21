package com.kodev.games.di

import com.kodev.games.data.GameRepository
import com.kodev.games.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): GameRepository {

        val remoteDataSource = RemoteDataSource()
        return GameRepository.getInstance(remoteDataSource)
    }
}
