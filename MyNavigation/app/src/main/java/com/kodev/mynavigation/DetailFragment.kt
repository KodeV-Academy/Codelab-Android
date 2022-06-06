package com.kodev.mynavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.kodev.mynavigation.databinding.FragmentDetailBinding
import com.kodev.mynavigation.databinding.FragmentHomeBinding
import com.kodev.mynavigation.pref.Constant.KEY_NAME
import com.kodev.mynavigation.pref.Utils.clearSinglePreference
import com.kodev.mynavigation.pref.Utils.getPreference
import com.kodev.mynavigation.pref.Utils.removePreference

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            tvName.text = getPreference(requireContext(), KEY_NAME)
            tvAddress.text = args.address
            tvUser.text = args.user.toString()
        }

        binding.btnClearSingle.setOnClickListener {
            clearSinglePreference(requireContext(), KEY_NAME)
        }

        binding.btnLogout.setOnClickListener {
            removePreference(requireContext())
        }
    }
}