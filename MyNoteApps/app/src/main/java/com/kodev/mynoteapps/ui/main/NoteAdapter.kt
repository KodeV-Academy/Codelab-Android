package com.kodev.mynoteapps.ui.main

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kodev.mynoteapps.database.Note
import com.kodev.mynoteapps.databinding.ItemNoteBinding
import com.kodev.mynoteapps.helper.NoteDiffCallback
import com.kodev.mynoteapps.ui.insert.NoteAddUpdateActivity

class NoteAdapter internal constructor(private val activity: Activity) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val listNote = ArrayList<Note>()

    fun setListData(listNote: List<Note>) {
        val diffCallback = NoteDiffCallback(this.listNote, listNote)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNote.clear()
        this.listNote.addAll(listNote)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            with(binding) {
                tvItemTitle.text = note.title
                tvItemDescription.text = note.description
                tvItemDate.text = note.date
                cvItemNote.setOnClickListener {
                    val intent = Intent(activity, NoteAddUpdateActivity::class.java)
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, adapterPosition)
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                    activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       holder.bind(listNote[position])
    }

    override fun getItemCount(): Int = listNote.size
}