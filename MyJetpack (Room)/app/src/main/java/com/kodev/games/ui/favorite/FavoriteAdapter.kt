package com.kodev.games.ui.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kodev.games.R
import com.kodev.games.data.source.local.entity.GameEntity
import com.kodev.games.databinding.LayoutListGameFavoriteBinding
import com.kodev.games.utils.Support.replaceArrayCode
import java.util.*

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private var listGame = ArrayList<GameEntity>()

    var onItemShareClick: ((GameEntity) -> Unit)? = null
    var onItemFavoriteClick: ((GameEntity) -> Unit)? = null
    var onItemClick: ((GameEntity) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(games: List<GameEntity>?) {
        if (games == null) return
        this.listGame.clear()
        this.listGame.addAll(games)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemsAcademyBinding = LayoutListGameFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val course = listGame[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listGame.size


    inner class FavoriteViewHolder(private val binding: LayoutListGameFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GameEntity) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(data.background_image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgGames)
                tvTitleGame.text = data.name
                tvRateGame.text = data.rating
                tvReleaseDate.text = data.released
                tvGenreGame.text = replaceArrayCode(data.genres)
                tvPlatformGame.text = replaceArrayCode(data.platforms)

                imgShareGame.setOnClickListener {
                    onItemShareClick?.invoke(data)
                }

                imgGamesFavorite.setOnClickListener {
                    onItemFavoriteClick?.invoke(data)
                }

                itemView.setOnClickListener {
                    onItemClick?.invoke(data)
                }
            }
        }
    }
}