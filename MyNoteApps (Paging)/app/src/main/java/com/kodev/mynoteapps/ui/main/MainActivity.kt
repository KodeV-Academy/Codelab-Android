package com.kodev.mynoteapps.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kodev.mynoteapps.R
import com.kodev.mynoteapps.database.Note
import com.kodev.mynoteapps.databinding.ActivityMainBinding
import com.kodev.mynoteapps.ui.ViewModelFactory
import com.kodev.mynoteapps.ui.insert.NoteAddUpdateActivity

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var notePagedListAdapter: NotePagedListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        noteAdapter = NoteAdapter(this@MainActivity)
        notePagedListAdapter = NotePagedListAdapter(this@MainActivity)

        val mainViewModel = obtainViewModel(this@MainActivity)
        mainViewModel.getAllNotes().observe(this, noteObserver)

        binding?.rvNotes?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = notePagedListAdapter
        }

        binding?.fabAdd?.setOnClickListener { view ->
            if (view.id == R.id.fab_add) {
                val intent = Intent(this@MainActivity, NoteAddUpdateActivity::class.java)
                startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_ADD)
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }

    private val noteObserver = Observer<PagedList<Note>> { noteList ->
        if (noteList != null) {
            notePagedListAdapter.submitList(noteList)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (requestCode == NoteAddUpdateActivity.REQUEST_ADD) {
                if (resultCode == NoteAddUpdateActivity.RESULT_ADD) {
                    showSnackbarMessage(getString(R.string.added))
                }
            } else if (requestCode == NoteAddUpdateActivity.REQUEST_UPDATE) {
                if (resultCode == NoteAddUpdateActivity.RESULT_UPDATE) {
                    showSnackbarMessage(getString(R.string.changed))
                } else if (resultCode == NoteAddUpdateActivity.RESULT_DELETE) {
                    showSnackbarMessage(getString(R.string.deleted))
                }
            }
        }
    }

    private fun showSnackbarMessage(message: String) {
        Snackbar.make(binding?.root as View, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}