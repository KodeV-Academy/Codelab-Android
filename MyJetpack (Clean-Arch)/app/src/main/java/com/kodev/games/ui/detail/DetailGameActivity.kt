package com.kodev.games.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kodev.games.R
import com.kodev.games.databinding.ActivityDetailGameBinding
import com.kodev.games.utils.Support.convertHtmlTagToText
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.kodev.games.core.data.source.local.entity.GameEntity
import com.kodev.games.ui.games.GameViewModel
import com.kodev.games.utils.Support.replaceArrayCode
import com.kodev.games.viewmodel.ViewModelFactory
import kotlin.math.abs

class DetailGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailGameBinding
    private lateinit var data: GameEntity
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this@DetailGameActivity)
        val viewModel = ViewModelProvider(this@DetailGameActivity, factory)[GameViewModel::class.java]

        binding.toolbar.setNavigationOnClickListener {
            this@DetailGameActivity.onBackPressed()
        }

        binding.imgButtonBack.setOnClickListener {
            this@DetailGameActivity.onBackPressed()
        }

        binding.fabFavorite.setOnClickListener {
            viewModel.updateGame(data, !isFavorite)
            populateFabButton(!isFavorite)
            if (isFavorite)
                Snackbar.make(it, "Berhasil Dihapus dari Favorit", Snackbar.LENGTH_SHORT).show()
            else
                Snackbar.make(it, "Berhasil Ditambah ke Favorit", Snackbar.LENGTH_SHORT).show()
        }

        populateToolbar()
        populateView()
    }

    private fun populateToolbar() {
        binding.apply {
            appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                    toolbar.visibility = View.VISIBLE
                } else {
                    toolbar.visibility = View.GONE
                }
            })
        }
    }

    private fun populateView() {
        data = intent.getParcelableExtra(EXTRA_DATA)!!
        binding.apply {
            Glide.with(this@DetailGameActivity)
                .load(data.background_image)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imgGames)

            toolbar.title = data.name
            tvTitleGame.text = data.name
            tvRateGame.text = data.rating
            tvReleaseDate.text = data.released
            tvGenreGame.text = replaceArrayCode(data.genres)
            tvPlatformGame.text = replaceArrayCode(data.platforms)
            tvMinimumGame.convertHtmlTagToText(data.minimum)
            tvRecommendedGame.convertHtmlTagToText(data.recommended)

            isFavorite = data.favorite
            populateFabButton(isFavorite)
        }
    }

    private fun populateFabButton(favorite: Boolean) {
        if (favorite)
            binding.fabFavorite.setImageResource(R.drawable.ic_baseline_favorite)
        else
            binding.fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_border)
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}