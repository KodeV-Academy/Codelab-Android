package com.kodev.mynoteapps.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import com.kodev.mynoteapps.repository.NoteRepository

class MainViewModel(application: Application): ViewModel() {

    private val mRepository = NoteRepository(application)

    fun getAllNotes() = mRepository.getAllNotes()

}