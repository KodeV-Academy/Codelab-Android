package com.kodev.mylivedatawithapi.ui

import android.content.Context
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kodev.mylivedatawithapi.R
import com.kodev.mylivedatawithapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.restaurant.observe(this) {
            binding.tvTitle.text = it.name
            binding.tvDescription.text = it.description
            Glide.with(this)
                .load("https://restaurant-api.dicoding.dev/images/large/${it.pictureId}")
                .into(binding.ivPicture)
        }

        mainViewModel.listReview.observe(this) { listReview ->
            val dataReview = listReview.map {
                "${it.name}:\n${it.review}"
            }
            binding.lvReview.adapter = ArrayAdapter(this, R.layout.item_review, dataReview)

        }

        mainViewModel.isLoading.observe(this) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        binding.btnSend.setOnClickListener {
            val review = binding.edReview.text.toString()
            if (review.isNotEmpty()) {
                mainViewModel.postReview(review)
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(it.windowToken, 0)
            }
        }
    }
}