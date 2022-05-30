package com.kodev.games.ui.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kodev.games.R
import com.kodev.games.data.GameEntity
import com.kodev.games.data.source.remote.response.DataGame
import com.kodev.games.databinding.LayoutListGameBinding
import java.util.*

class GameAdapter : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {
    private var listGame = ArrayList<DataGame>()
    var onItemClick: ((DataGame) -> Unit)? = null

    fun setData(games: List<DataGame>?) {
        if (games == null) return
        this.listGame.clear()
        this.listGame.addAll(games)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val itemsAcademyBinding = LayoutListGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val course = listGame[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listGame.size


    inner class GameViewHolder(private val binding: LayoutListGameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataGame) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(data.background_image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgGames)
                tvTitleGame.text = data.name
                tvRateGame.text = data.rating.toString()
                tvReleaseDate.text = data.released

                for (i in data.genres) {
                    tvGenreGame.text = i.name
                }

                for (i in data.platforms) {
                    tvPlatformGame.text = i.platform.name
                }

                itemView.setOnClickListener {
                    onItemClick?.invoke(data)
                }
            }
        }
    }
}
