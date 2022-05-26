package com.kodev.mynoteapps.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kodev.mynoteapps.ui.insert.NoteAddUpdateViewModel
import com.kodev.mynoteapps.ui.main.MainViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(application) as T
        } else if (modelClass.isAssignableFrom(NoteAddUpdateViewModel::class.java)) {
            NoteAddUpdateViewModel(application) as T
        } else {
            throw IllegalArgumentException("Unkown ViewModel Class: ${modelClass.name}")
        }
    }
}