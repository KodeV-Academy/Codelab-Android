package com.kodev.mynoteapps.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kodev.mynoteapps.R
import com.kodev.mynoteapps.database.Note
import com.kodev.mynoteapps.databinding.ActivityMainBinding
import com.kodev.mynoteapps.ui.ViewModelFactory
import com.kodev.mynoteapps.ui.insert.NoteAddUpdateActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var noteAdapter: NoteAdapter
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        noteAdapter = NoteAdapter(this@MainActivity)
        mainViewModel = obtainViewModel(this@MainActivity)

        binding?.rvNotes?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = noteAdapter
        }

        mainViewModel.getAllNotes().observe(this@MainActivity) {
            if (it != null) {
                noteAdapter.setListData(it)
            }
        }

        binding?.fabAdd?.setOnClickListener {
            val intent = Intent(this@MainActivity, NoteAddUpdateActivity::class.java)
            startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_ADD)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null) {
            if (requestCode == NoteAddUpdateActivity.REQUEST_ADD) {
                if (resultCode == NoteAddUpdateActivity.RESULT_ADD) {
                    showSnackBarMessage(getString(R.string.added))
                }
            } else if (requestCode == NoteAddUpdateActivity.REQUEST_UPDATE) {
                if (resultCode == NoteAddUpdateActivity.RESULT_UPDATE) {
                    showSnackBarMessage(getString(R.string.changed))
                } else if (resultCode == NoteAddUpdateActivity.RESULT_DELETED) {
                    showSnackBarMessage(getString(R.string.deleted))
                }
            }
        }
    }

    private fun obtainViewModel(mainActivity: MainActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(mainActivity.application)
        return ViewModelProvider(mainActivity, factory)[MainViewModel::class.java]
    }

    private fun showSnackBarMessage(message: String) {
        Snackbar.make(binding?.root as View, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}