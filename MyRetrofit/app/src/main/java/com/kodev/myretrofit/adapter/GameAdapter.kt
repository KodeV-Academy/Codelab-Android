package com.kodev.myretrofit.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kodev.myretrofit.databinding.ListGameBinding
import com.kodev.myretrofit.model.DataGame

class GameAdapter : RecyclerView.Adapter<GameAdapter.ListViewHolder>() {

    private val listData = ArrayList<DataGame>()

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(list: List<DataGame>) {
        listData.clear()
        listData.addAll(list)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(private val binding: ListGameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataGame) {
            binding.apply {
                Glide.with(imgGames)
                    .load(data.background_image)
                    .into(imgGames)

                tvTitleGame.text = data.name
                tvRateGame.text = data.rating.toString()
                tvReleaseGame.text = data.released

                val genre = ArrayList<String>()
                for (i in data.genres) {
                    genre.add(i.name)
                }
                tvGenreGame.text = genre.toString().replace("[", "").replace("]", "")

                tvRequirements.text = data.platforms[0].requirements_en?.minimum
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ListGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameAdapter.ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size
}