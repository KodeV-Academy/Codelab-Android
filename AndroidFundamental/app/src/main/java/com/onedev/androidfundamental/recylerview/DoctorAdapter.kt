package com.onedev.androidfundamental.recylerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onedev.androidfundamental.databinding.LayoutListDoctorBinding

class DoctorAdapter : RecyclerView.Adapter<DoctorAdapter.ListViewHolder>() {

    private val listData = ArrayList<Data.Doctor>()
    var onItemClick: ((Data.Doctor) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(list: List<Data.Doctor>) {
        listData.clear()
        listData.addAll(list)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: LayoutListDoctorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data.Doctor) {
            Glide.with(binding.imgDoctor)
                .load(data.image)
                .into(binding.imgDoctor)
            binding.tvName.text =  data.name
            binding.tvSpecialist.text = data.specialist
            binding.tvExperience.text = data.experience
            binding.tvPrice.text = data.price

            itemView.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = LayoutListDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

}