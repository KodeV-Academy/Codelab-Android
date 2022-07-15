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
import com.onedev.mycrud.api.response.RequestAddUser
import com.onedev.mycrud.api.response.RequestEditUser
import com.onedev.mycrud.databinding.FragmentAddUserBinding
import com.onedev.mycrud.databinding.FragmentDetailUserBinding
import com.onedev.mycrud.databinding.FragmentEditUserBinding
import com.onedev.mycrud.databinding.FragmentUserBinding
import com.onedev.mycrud.utils.Support.showToast

class AddUserFragment : Fragment() {

    private lateinit var binding: FragmentAddUserBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        binding = FragmentAddUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.btnEdit.setOnClickListener {
            val name = binding.edtName.text.toString()
            val email = binding.edtEmail.text.toString()
            val handPhone = binding.edtHandphone.text.toString()
            val holding = binding.edtHolding.text.toString()
            val password = binding.edtPassword.text.toString()

            val requestAddUser = RequestAddUser(
                email, holding, name, password, handPhone, "photo"
            )

            userViewModel.addUser(requestAddUser)
            userViewModel.statusAddUser.observe(viewLifecycleOwner) {
                requireContext().showToast(it)
                requireActivity().onBackPressed()
            }
        }
    }
}