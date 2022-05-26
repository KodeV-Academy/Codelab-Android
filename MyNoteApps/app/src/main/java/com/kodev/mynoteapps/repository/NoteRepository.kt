package com.kodev.mynoteapps.repository

import android.app.Application
import com.kodev.mynoteapps.database.Note
import com.kodev.mynoteapps.database.NoteDao
import com.kodev.mynoteapps.database.NoteRoomDatabase
import java.util.concurrent.Executors

class NoteRepository(application: Application) {

    private val mNotesDao: NoteDao
    private val executorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteRoomDatabase.getDatabase(application)
        mNotesDao = db.noteDao()
    }

    fun getAllNotes() = mNotesDao.getAllNote()

    fun insert(note: Note) {
        executorService.execute { mNotesDao.insert(note) }
    }

    fun delete(note: Note) {
        executorService.execute { mNotesDao.delete(note) }
    }

    fun update(note: Note) {
        executorService.execute { mNotesDao.update(note) }
    }

}