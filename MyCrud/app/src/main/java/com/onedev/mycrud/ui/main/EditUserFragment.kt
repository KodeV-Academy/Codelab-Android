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
import com.onedev.mycrud.api.response.RequestEditUser
import com.onedev.mycrud.databinding.FragmentDetailUserBinding
import com.onedev.mycrud.databinding.FragmentEditUserBinding
import com.onedev.mycrud.databinding.FragmentUserBinding
import com.onedev.mycrud.utils.Support.showToast

class EditUserFragment : Fragment() {

    private lateinit var binding: FragmentEditUserBinding
    private val args : EditUserFragmentArgs by navArgs()
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        binding = FragmentEditUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.edtName.setText(args.dataUser.user_name)
        binding.edtEmail.setText(args.dataUser.user_email)
        binding.edtHandphone.setText(args.dataUser.user_phone)
        binding.edtHolding.setText(args.dataUser.user_holding)
        binding.edtPassword.setText(args.dataUser.user_password)

        binding.btnEdit.setOnClickListener {
            val name = binding.edtName.text.toString()
            val email = binding.edtEmail.text.toString()
            val handPhone = binding.edtHandphone.text.toString()
            val holding = binding.edtHolding.text.toString()
            val password = binding.edtPassword.text.toString()

            val requestEditUser = RequestEditUser(
                email, holding, name, password, handPhone, "photo"
            )

            userViewModel.editUser(args.dataUser.id.toString(), requestEditUser)
            userViewModel.statusEditUser.observe(viewLifecycleOwner) {
                requireContext().showToast(it)
                findNavController().navigate(EditUserFragmentDirections.actionEditUserFragmentToBookFragment())
            }
        }
    }
}