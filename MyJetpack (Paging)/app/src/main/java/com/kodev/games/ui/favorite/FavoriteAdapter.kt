package com.kodev.games.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kodev.games.R
import com.kodev.games.data.source.local.entity.GameEntity
import com.kodev.games.databinding.LayoutListGameFavoriteBinding
import com.kodev.games.utils.Support.replaceArrayCode

class FavoriteAdapter :
    PagedListAdapter<GameEntity, FavoriteAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {

    var onItemShareClick: ((GameEntity) -> Unit)? = null
    var onItemFavoriteClick: ((GameEntity) -> Unit)? = null
    var onItemClick: ((GameEntity) -> Unit)? = null

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GameEntity>() {
            override fun areItemsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemsAcademyBinding = LayoutListGameFavoriteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoriteViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val game = getItem(position)
        if (game != null) {
            holder.bind(game)
        }
    }

    inner class FavoriteViewHolder(private val binding: LayoutListGameFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GameEntity) {
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