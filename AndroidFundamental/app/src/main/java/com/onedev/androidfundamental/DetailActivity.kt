package com.onedev.androidfundamental

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onedev.androidfundamental.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("NAMA")
        val kelas = intent.getStringExtra("KELAS")
        val nomor = intent.getIntExtra("NOMOR", 0)

        binding.apply {
            tvNama.text = name
            tvClass.text = kelas
            tvNumber.text = nomor.toString()
        }
    }
}