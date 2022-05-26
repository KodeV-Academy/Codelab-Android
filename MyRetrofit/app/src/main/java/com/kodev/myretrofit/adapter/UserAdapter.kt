package com.kodev.myretrofit.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kodev.myretrofit.R
import com.kodev.myretrofit.databinding.ListGameBinding
import com.kodev.myretrofit.databinding.ListUserBinding
import com.kodev.myretrofit.model.DataGame
import com.kodev.myretrofit.model.DataUser

class UserAdapter : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

    private val listData = ArrayList<DataUser>()

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(list: List<DataUser>) {
        listData.clear()
        listData.addAll(list)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: ListUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataUser) {
            binding.apply {
                Glide.with(imgUser)
                    .load(data.avatar)
                    .into(imgUser)

                tvName.text = itemView.context.getString(R.string.fullname, data.first_name, data.last_name)
                tvEmail.text = data.email
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAdapter.ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size
}