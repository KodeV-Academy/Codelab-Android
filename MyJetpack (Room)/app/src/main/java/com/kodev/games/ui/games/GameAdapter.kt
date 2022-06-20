package com.kodev.games.ui.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kodev.games.R
import com.kodev.games.data.source.local.entity.GameEntity
import com.kodev.games.databinding.LayoutListGameBinding
import com.kodev.games.utils.Support.replaceArrayCode

class GameAdapter : PagedListAdapter<GameEntity, GameAdapter.GameViewHolder>(DIFF_CALLBACK) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val itemsAcademyBinding = LayoutListGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = getItem(position)
        if (game != null) {
            holder.bind(game)
        }
    }

    inner class GameViewHolder(private val binding: LayoutListGameBinding) : RecyclerView.ViewHolder(binding.root) {
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

                itemView.setOnClickListener {
                    onItemClick?.invoke(data)
                }
            }
        }
    }
}

