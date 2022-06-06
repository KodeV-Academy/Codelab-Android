package com.kodev.mynavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kodev.mynavigation.databinding.FragmentHomeBinding
import com.kodev.mynavigation.pref.Constant.KEY_ADDRESS
import com.kodev.mynavigation.pref.Constant.KEY_NAME
import com.kodev.mynavigation.pref.Constant.KEY_TOKEN
import com.kodev.mynavigation.pref.Utils.putPreference

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val token = "123456789"
            val name = binding.edtName.text.toString()
            val address = binding.edtAddress.text.toString()

            putPreference(requireContext(), KEY_NAME, name)
            putPreference(requireContext(), KEY_ADDRESS, address)
            putPreference(requireContext(), KEY_TOKEN, token)

            val user = User(1, "KodeV", 20)
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(name, address, user)
            findNavController().navigate(action)
        }
    }
}