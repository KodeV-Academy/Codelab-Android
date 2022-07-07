package com.onedev.mycrud.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onedev.mycrud.api.response.BooksResponse
import com.onedev.mycrud.databinding.LayoutListBookBinding

class BookAdapter : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private var listData = ArrayList<BooksResponse.Data>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<BooksResponse.Data>?) {
        if (data == null) return
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: LayoutListBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: BooksResponse.Data) {
            Glide.with(itemView.context)
                .load(data.linkImage)
                .into(binding.imgBook)

            binding.tvTitle.text = data.title
            binding.tvDescription.text = data.author
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutListBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

}