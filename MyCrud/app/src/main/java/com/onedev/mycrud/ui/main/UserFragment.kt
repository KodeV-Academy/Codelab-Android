package com.onedev.mycrud.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.onedev.mycrud.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private val userViewModel: UserViewModel by viewModels()
    private lateinit var binding: FragmentUserBinding
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        loadBook()
    }

    private fun loadBook() {
        userViewModel.getUsers()
        userViewModel.dataUser.observe(viewLifecycleOwner) { response ->
            if (response.isNotEmpty()) {
                userAdapter.setData(response)
                binding.rvBook.visibility = View.VISIBLE
                binding.progressCircular.visibility = View.GONE
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userAdapter = UserAdapter()
        binding.rvBook.apply {
            adapter = userAdapter
        }
        userAdapter.onItemClick = {
            val action = UserFragmentDirections.actionBookFragmentToDetailUserFragment(it)
            findNavController().navigate(action)
        }

        binding.fabAddUser.setOnClickListener {
            findNavController().navigate(UserFragmentDirections.actionBookFragmentToAddUserFragment())
        }
    }
}