package com.moon.myapplication.ui.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.moon.myapplication.databinding.FragmentFoodDetailBinding

class FoodDetailFragment : Fragment() {
    private var _binding: FragmentFoodDetailBinding? = null
    private val binding get() = _binding!!
    private val args: FoodDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext())
            .load(args.dataFood.image)
            .into(binding.imgFood)
        binding.tvFood.text = args.dataFood.name
        binding.tvDescription.text = args.dataFood.description
    }
}