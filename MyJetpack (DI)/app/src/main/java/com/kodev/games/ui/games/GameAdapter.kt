package com.kodev.games.ui.games

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kodev.games.R
import com.kodev.games.core.domain.model.Game
import com.kodev.games.databinding.LayoutListGameBinding
import com.kodev.games.utils.Support.replaceArrayCode

class GameAdapter : RecyclerView.Adapter<GameAdapter.GameViewHolder>(){

    private var listData = ArrayList<Game>()
    var onItemClick: ((Game) -> Unit)? = null


    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Game>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val itemsAcademyBinding =
            LayoutListGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    inner class GameViewHolder(private val binding: LayoutListGameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Game) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(data.background_image)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgGames)
                tvTitleGame.text = data.name
                tvRateGame.text = data.rating
                tvReleaseDate.text = data.released
                tvGenreGame.text = replaceArrayCode(data.genres)
                tvPlatformGame.text = replaceArrayCode(data.platforms)

                itemView.setOnClickListener {
                    onItemClick?.invoke(data)
                }
            }
        }
    }

    override fun getItemCount(): Int = listData.size
}

