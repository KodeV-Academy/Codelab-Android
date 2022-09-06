package com.onedev.mycrud.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onedev.mycrud.api.response.ResponseCustomerList
import com.onedev.mycrud.api.response.UserListResponse
import com.onedev.mycrud.databinding.LayoutListCustomerBinding
import com.onedev.mycrud.databinding.LayoutListUserBinding

class CustomerAdapter : RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {

    private var listData = ArrayList<ResponseCustomerList.Data>()
    var onItemClick: ((ResponseCustomerList.Data) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<ResponseCustomerList.Data>?) {
        if (data == null) return
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: LayoutListCustomerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ResponseCustomerList.Data) {

            binding.tvName.text = data.name
            binding.tvCity.text = data.city
            itemView.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutListCustomerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

}