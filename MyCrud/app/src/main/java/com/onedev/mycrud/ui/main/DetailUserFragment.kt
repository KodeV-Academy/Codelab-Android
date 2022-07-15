package com.onedev.mycrud.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.onedev.mycrud.R
import com.onedev.mycrud.databinding.FragmentDetailUserBinding
import com.onedev.mycrud.databinding.FragmentUserBinding
import com.onedev.mycrud.utils.Support.showToast

class DetailUserFragment : Fragment() {

    private lateinit var binding: FragmentDetailUserBinding
    private val args : DetailUserFragmentArgs by navArgs()
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        binding = FragmentDetailUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.tvName.text = args.dataUser.user_name
        binding.tvEmail.text = args.dataUser.user_email
        binding.tvHandphone.text = args.dataUser.user_phone
        binding.tvHolding.text = args.dataUser.user_holding
        binding.tvPassword.text = args.dataUser.user_password

        binding.btnEdit.setOnClickListener {
            val action = DetailUserFragmentDirections.actionDetailUserFragmentToEditUserFragment(args.dataUser)
            findNavController().navigate(action)
        }

        binding.btnDelete.setOnClickListener {
            binding.progressCircular.visibility = View.VISIBLE
            binding.btnDelete.visibility = View.GONE
            userViewModel.deleteUser(args.dataUser.id.toString())
            userViewModel.statusDeleteUser.observe(viewLifecycleOwner) {
                if (it == 1) {
                    requireContext().showToast("Data Berhasil Dihapus")
                    requireActivity().onBackPressed()
                } else {
                    requireContext().showToast("Data Tidak Bisa Dihapus Karena ada Relasi")
                    binding.progressCircular.visibility = View.GONE
                    binding.btnDelete.visibility = View.VISIBLE
                }
            }
        }
    }
}