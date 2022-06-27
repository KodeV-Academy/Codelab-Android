package com.kodev.games.ui.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kodev.games.R
import com.kodev.games.core.domain.model.Game
import com.kodev.games.databinding.LayoutListGameFavoriteBinding
import com.kodev.games.utils.Support.replaceArrayCode

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var listData = ArrayList<Game>()
    var onItemClick: ((Game) -> Unit)? = null
    var onItemShareClick: ((Game) -> Unit)? = null
    var onItemFavoriteClick: ((Game) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Game>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
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
        holder.bind(listData[position])
    }

    inner class FavoriteViewHolder(private val binding: LayoutListGameFavoriteBinding) :
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

    override fun getItemCount(): Int = listData.size
}