package com.kodev.games.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kodev.games.R
import com.kodev.games.data.GameEntity
import com.kodev.games.databinding.LayoutListGameFavoriteBinding
import java.util.*

class FavoriteAdapter() : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private var listGame = ArrayList<GameEntity>()

    var onItemShareClick: ((GameEntity) -> Unit)? = null

    fun setData(games: List<GameEntity>?) {
        if (games == null) return
        this.listGame.clear()
        this.listGame.addAll(games)
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
                tvGenreGame.text = data.genres
                tvPlatformGame.text = data.platforms

                imgShareGame.setOnClickListener {
                    onItemShareClick?.invoke(data)
                }
            }
        }
    }
}