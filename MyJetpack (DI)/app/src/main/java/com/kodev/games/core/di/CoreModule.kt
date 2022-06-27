package com.kodev.games.core.di

import androidx.room.Room
import com.kodev.games.core.data.source.GameRepository
import com.kodev.games.core.data.source.local.LocalDataSource
import com.kodev.games.core.data.source.local.room.GameDatabase
import com.kodev.games.core.data.source.remote.RemoteDataSource
import com.kodev.games.core.data.source.remote.api.ApiService
import com.kodev.games.core.domain.repository.IGameRepository
import com.kodev.games.core.domain.usecase.GameInteractor
import com.kodev.games.core.domain.usecase.GameUseCase
import com.kodev.games.ui.detail.DetailGameViewModel
import com.kodev.games.ui.favorite.FavoriteViewModel
import com.kodev.games.ui.games.GameViewModel
import com.kodev.games.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    factory { get<GameDatabase>().gameDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java,
            "Games.db"
        ).build()
    }
}

val networkModule = module {
    single {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IGameRepository> { GameRepository(get(), get(), get()) }
}

val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
}

val viewModelModule = module {
    viewModel { GameViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailGameViewModel(get()) }
}