package com.onedev.mycrud.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.onedev.mycrud.databinding.FragmentBookBinding
import com.onedev.mycrud.ui.login.LoginViewModel

class BookFragment : Fragment() {

    private val bookViewModel: BookViewModel by viewModels()
    private lateinit var binding: FragmentBookBinding
    private lateinit var bookAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        loadBook()
    }

    private fun loadBook() {
        bookViewModel.getBooks()
        bookViewModel.dataBooks.observe(viewLifecycleOwner) { response ->
            if (response.isNotEmpty()) {
                bookAdapter.setData(response)
                binding.rvBook.visibility = View.VISIBLE
                binding.progressCircular.visibility = View.GONE
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookAdapter = BookAdapter()
        binding.rvBook.apply {
            adapter = bookAdapter
        }
    }
}