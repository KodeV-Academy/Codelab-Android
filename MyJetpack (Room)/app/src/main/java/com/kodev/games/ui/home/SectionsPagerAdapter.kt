package com.kodev.games.ui.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kodev.games.ui.favorite.FavoriteFragment
import com.kodev.games.ui.games.GameFragment

class SectionPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = GameFragment()
            1 -> fragment = FavoriteFragment()
        }
        return fragment as Fragment
    }

}