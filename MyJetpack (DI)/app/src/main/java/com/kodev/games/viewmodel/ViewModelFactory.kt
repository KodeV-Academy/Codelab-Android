package com.kodev.games.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kodev.games.core.data.source.GameRepository
import com.kodev.games.core.di.Injection
import com.kodev.games.core.domain.usecase.GameUseCase
import com.kodev.games.ui.detail.DetailGameViewModel
import com.kodev.games.ui.favorite.FavoriteViewModel
import com.kodev.games.ui.games.GameViewModel

class ViewModelFactory private constructor(private val gameUseCase: GameUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideTourismUseCase(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(GameViewModel::class.java) -> {
                GameViewModel(gameUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(gameUseCase) as T
            }
            modelClass.isAssignableFrom(DetailGameViewModel::class.java) -> {
                DetailGameViewModel(gameUseCase) as T
            }
            else -> throw Throwable("Unkown ViewModel Class: " + modelClass.name)
        }
    }
}