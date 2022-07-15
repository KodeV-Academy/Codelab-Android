package com.onedev.mycrud.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onedev.mycrud.api.response.UserListResponse
import com.onedev.mycrud.databinding.LayoutListUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var listData = ArrayList<UserListResponse.Data>()
    var onItemClick: ((UserListResponse.Data) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<UserListResponse.Data>?) {
        if (data == null) return
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: LayoutListUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserListResponse.Data) {
//            Glide.with(itemView.context)
//                .load(data.linkImage)
//                .into(binding.imgBook)

            binding.tvName.text = data.user_name
            binding.tvEmail.text = data.user_email
            itemView.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

}