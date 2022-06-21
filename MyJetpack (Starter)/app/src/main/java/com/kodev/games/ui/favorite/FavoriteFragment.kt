package com.kodev.games.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import com.kodev.games.R
import com.kodev.games.databinding.FragmentFavoriteBinding
import com.kodev.games.utils.DataDummy

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val games = DataDummy.generateDataGames()
        val favoriteAdapter = FavoriteAdapter()
        favoriteAdapter.setData(games)

        binding.rvFavorite.apply {
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }

        favoriteAdapter.onItemShareClick = {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("Bagikan Game Ini Sekarang.")
                .setText(resources.getString(R.string.share_text, it.name))
                .startChooser()
        }
    }
}

