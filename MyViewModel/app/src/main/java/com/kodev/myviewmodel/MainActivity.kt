package com.kodev.myviewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kodev.myviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnCalculate.setOnClickListener {
                val width = edtWidth.text.toString()
                val height = edtHeight.text.toString()
                val length = edtLength.text.toString()
                when {
                    width.isEmpty() -> {
                        edtWidth.error = "Masih kosong"
                    }
                    height.isEmpty() -> {
                        edtHeight.error = "Masih kosong"
                    }
                    length.isEmpty() -> {
                        edtLength.error = "Masih kosong"
                    }
                    else -> {
                        val result = width.toInt() * height.toInt() * length.toInt()
                        binding.tvResult.text = result.toString()
                    }
                }
            }
        }
    }
}