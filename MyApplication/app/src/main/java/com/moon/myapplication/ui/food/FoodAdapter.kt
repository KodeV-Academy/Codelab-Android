package com.moon.myapplication.ui.food

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moon.myapplication.databinding.LayoutListFoodBinding

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.HomeViewHolder>() {

    private val datas = ArrayList<FoodData>()
    var onItemClick: ((FoodData) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(listData: List<FoodData>?) {
        if (listData == null) return
        datas.clear()
        datas.addAll(listData)
        notifyDataSetChanged()
    }

    inner class HomeViewHolder(private val binding: LayoutListFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FoodData) {
            Glide.with(itemView.context)
                .load(data.image)
                .into(binding.imgFood)
            binding.tvFood.text = data.name

            itemView.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding =
            LayoutListFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

    fun filterList(filteredList: ArrayList<FoodData>) = setListData(filteredList)

}