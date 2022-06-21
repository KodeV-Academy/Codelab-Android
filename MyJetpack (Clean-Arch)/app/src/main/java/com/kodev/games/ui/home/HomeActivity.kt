package com.kodev.games.ui.home

import android.os.Bundle
import android.util.Log
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.kodev.games.R
import com.kodev.games.databinding.ActivityHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionPagerAdapter(this@HomeActivity)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        val jsonHelper = JsonHelper(this@HomeActivity)
        val response = jsonHelper.loadGame()
        Log.d("jsonHelper", "onCreate: $response")
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.game,
            R.string.favorite
        )
    }
}