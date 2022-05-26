package com.kodev.mynoteapps.ui.insert

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kodev.mynoteapps.R
import com.kodev.mynoteapps.database.Note
import com.kodev.mynoteapps.databinding.ActivityNoteAddUpdateBinding
import com.kodev.mynoteapps.helper.DateHelper
import com.kodev.mynoteapps.ui.ViewModelFactory

class NoteAddUpdateActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var noteAddUpdateViewModel: NoteAddUpdateViewModel
    private var _binding: ActivityNoteAddUpdateBinding? = null
    private val binding get() = _binding

    companion object {
        const val EXTRA_NOTE = "extra_note"
        const val EXTRA_POSITION = "extra_position"
        const val RESULT_ADD = 100
        const val REQUEST_ADD = 101
        const val RESULT_UPDATE = 200
        const val REQUEST_UPDATE = 201
        const val RESULT_DELETED= 301
        const val ALERT_DIALOG_CLOSE = 400
        const val ALERT_DIALOG_DELETE = 500
    }

    private var isEdit = false
    private var note: Note? = null
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNoteAddUpdateBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        noteAddUpdateViewModel = obtainViewModel(this@NoteAddUpdateActivity)

        note = intent.getParcelableExtra(EXTRA_NOTE)
        if (note != null) {
            position = intent.getIntExtra(EXTRA_POSITION, 0)
            isEdit = true
        } else {
            note = Note()
        }

        val actionBarTitle: String
        val btnTitle: String
        if (isEdit) {
            actionBarTitle = getString(R.string.change)
            btnTitle = getString(R.string.update)
            if (note != null) {
                note?.let {
                    binding?.edtTitle?.setText(it.title)
                    binding?.edtDescription?.setText(it.description)
                }
            }
        } else {
            actionBarTitle = getString(R.string.add)
            btnTitle = getString(R.string.save)
        }

        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.btnSubmit?.text = btnTitle

        binding?.btnSubmit?.setOnClickListener(this)
    }

    private fun obtainViewModel(activity: AppCompatActivity): NoteAddUpdateViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[NoteAddUpdateViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding?.btnSubmit -> {
                val title = binding?.edtTitle?.text.toString()
                val description = binding?.edtDescription?.text.toString()

                if (title.isEmpty()) {
                    binding?.edtTitle?.error = getString(R.string.empty)
                } else if (description.isEmpty()) {
                    binding?.edtDescription?.error = getString(R.string.empty)
                } else {
                    note?.let {
                        it.title = title
                        it.description = description
                    }
                    val intent = Intent().apply {
                        putExtra(EXTRA_NOTE, note)
                        putExtra(EXTRA_NOTE, position)
                    }

                    if (isEdit) {
                        noteAddUpdateViewModel.update(note as Note)
                        setResult(RESULT_UPDATE, intent)
                        finish()
                    } else {
                        note?.let {
                            it.date = DateHelper.getCurrentDate()
                        }
                        noteAddUpdateViewModel.insert(note as Note)
                        setResult(RESULT_ADD, intent)
                        finish()
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (isEdit) {
            menuInflater.inflate(R.menu.menu_delete, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> {
                showAlertDialog(ALERT_DIALOG_DELETE)
            }
            android.R.id.home -> {
                showAlertDialog(ALERT_DIALOG_CLOSE)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE)
    }

    private fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String
        if (isDialogClose) {
            dialogTitle = getString(R.string.cancel)
            dialogMessage = getString(R.string.message_cancel)
        } else {
            dialogTitle = getString(R.string.delete)
            dialogMessage = getString(R.string.message_delete)
        }

        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                if (!isDialogClose) {
                    noteAddUpdateViewModel.delete(note as Note)
                    val intent = Intent()
                    intent.putExtra(EXTRA_POSITION, position)
                    setResult(RESULT_DELETED, intent)
                }
                finish()
            }
            setNegativeButton(getString(R.string.no)) { dialog, _ ->
                dialog.cancel()
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }
}