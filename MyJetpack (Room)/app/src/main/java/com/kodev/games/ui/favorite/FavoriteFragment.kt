package com.kodev.games.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.kodev.games.R
import com.kodev.games.databinding.FragmentFavoriteBinding
import com.kodev.games.ui.detail.DetailGameActivity
import com.kodev.games.ui.games.GameViewModel
import com.kodev.games.utils.DataDummy
import com.kodev.games.viewmodel.ViewModelFactory
import com.kodev.games.vo.Status

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

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this@FavoriteFragment, factory)[GameViewModel::class.java]

        val favoriteAdapter = FavoriteAdapter()
        binding.rvFavorite.apply {
            adapter = favoriteAdapter
        }

        favoriteAdapter.onItemClick = {
            val intent = Intent(requireContext(), DetailGameActivity::class.java)
            intent.putExtra(DetailGameActivity.EXTRA_DATA, it)
            requireActivity().startActivity(intent)
        }

        viewModel.getFavoriteGame().observe(viewLifecycleOwner) {
            favoriteAdapter.setData(it)
            binding.progressCircular.visibility = View.GONE
        }
    }
}

