package com.kodev.games.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kodev.games.data.source.GameRepository
import com.kodev.games.di.Injection
import com.kodev.games.ui.detail.DetailGameViewModel
import com.kodev.games.ui.favorite.FavoriteViewModel
import com.kodev.games.ui.games.GameViewModel

class ViewModelFactory private constructor(private val gameRepository: GameRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(GameViewModel::class.java) -> {
                GameViewModel(gameRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(gameRepository) as T
            }
            modelClass.isAssignableFrom(DetailGameViewModel::class.java) -> {
                DetailGameViewModel(gameRepository) as T
            }
            else -> throw Throwable("Unkown ViewModel Class: " + modelClass.name)
        }
    }
}