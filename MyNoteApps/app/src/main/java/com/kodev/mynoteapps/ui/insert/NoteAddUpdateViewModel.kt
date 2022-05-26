package com.kodev.mynoteapps.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.kodev.mynoteapps.database.Note
import com.kodev.mynoteapps.repository.NoteRepository

class NoteAddUpdateViewModel(application: Application): ViewModel() {

    private val mRepository = NoteRepository(application)

    fun insert(note: Note) {
        mRepository.insert(note)
    }

    fun update(note: Note) {
        mRepository.update(note)
    }

    fun delete(note: Note) {
        mRepository.delete(note)
    }

}