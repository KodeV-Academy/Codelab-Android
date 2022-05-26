package com.kodev.games.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kodev.games.R
import com.kodev.games.data.GameEntity
import com.kodev.games.databinding.ActivityDetailGameBinding
import com.kodev.games.utils.Support.convertHtmlTagToText
import com.google.android.material.appbar.AppBarLayout
import com.kodev.games.data.source.remote.response.DataGame
import kotlin.math.abs

class DetailGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            this@DetailGameActivity.onBackPressed()
        }

        binding.imgButtonBack.setOnClickListener {
            this@DetailGameActivity.onBackPressed()
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
        val data = intent.getParcelableExtra<DataGame>(EXTRA_DATA)
        if (data != null) {
            binding.apply {
                Glide.with(this@DetailGameActivity)
                    .load(data.background_image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(imgGames)

                toolbar.title = data.name
                tvTitleGame.text = data.name
                tvRateGame.text = data.rating.toString()
                tvReleaseDate.text = data.released
                for (i in data.genres) {
                    tvGenreGame.text = i.name
                }

                for (i in data.platforms) {
                    tvPlatformGame.text = i.platform.name
                }

                tvMinimumGame.convertHtmlTagToText(data.platforms[0].requirements_en?.minimum.toString())
                tvRecommendedGame.convertHtmlTagToText(data.platforms[0].requirements_en?.recommended.toString())
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}