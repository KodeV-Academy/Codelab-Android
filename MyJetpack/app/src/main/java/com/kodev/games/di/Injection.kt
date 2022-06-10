package com.kodev.games.di

import android.content.Context
import com.kodev.games.data.source.GameRepository
import com.kodev.games.data.source.remote.RemoteDataSource
import com.kodev.games.utils.JsonHelper

object Injection {

    fun provideRepository(context: Context): GameRepository {
        val remoteDataSource = RemoteDataSource()
        return GameRepository.getInstance(remoteDataSource)
    }

}