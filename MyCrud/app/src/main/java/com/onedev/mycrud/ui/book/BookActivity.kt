package com.onedev.mycrud.ui.book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onedev.mycrud.databinding.ActivityBookBinding

class BookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}