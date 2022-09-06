package com.onedev.mycrud.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.onedev.mycrud.databinding.FragmentCustomerBinding

class CustomerFragment : Fragment() {
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var customerAdapter: CustomerAdapter
    private lateinit var binding: FragmentCustomerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        customerAdapter = CustomerAdapter()
        binding.rvCustomer.adapter = customerAdapter

        loadCustomer()
    }

    private fun loadCustomer() {
        userViewModel.getCustomers()
        userViewModel.responseListCustomer.observe(viewLifecycleOwner) { response ->
            if (response.isNotEmpty()) {
                customerAdapter.setData(response)
            }
        }
    }
}