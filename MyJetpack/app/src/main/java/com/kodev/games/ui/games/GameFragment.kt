package com.kodev.games.ui.games

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kodev.games.databinding.FragmentGameBinding
import com.kodev.games.ui.detail.DetailGameActivity
import com.kodev.games.viewmodel.ViewModelFactory

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[GameViewModel::class.java]

        val gameAdapter = GameAdapter()
        viewModel.getGames().observe(viewLifecycleOwner) {
            gameAdapter.setData(it.results)
        }
        binding.rvGame.adapter = gameAdapter

        gameAdapter.onItemClick = {
            val intent = Intent(requireContext(), DetailGameActivity::class.java)
            intent.putExtra(DetailGameActivity.EXTRA_DATA, it)
            requireActivity().startActivity(intent)
        }
    }
}

