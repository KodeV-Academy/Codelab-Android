package com.onedev.androidfundamental

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.onedev.androidfundamental.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private lateinit var tvText: TextView
//    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        tvText = findViewById(R.id.tv_text) // findViewById (Temukan view berdasarkan id di xml)
//        btnNext = findViewById(R.id.btn_next)
//
//        btnNext.setOnClickListener {
//            tvText.text = "KodeV Academy"
//        }

        binding.btnNext.setOnClickListener {
//            binding.tvText.text = "Ini ViewBinding"
            val intent = Intent(applicationContext, BlankActivity::class.java)
            startActivity(intent)
        }

        binding.btnIntentWithData.setOnClickListener {
            val intent = Intent(applicationContext, DetailActivity::class.java)
            intent.putExtra("NAMA", "KodeV Academy")
            intent.putExtra("KELAS", "Android Developer")
            intent.putExtra("NOMOR", 100)
            startActivity(intent)
        }

        binding.btnImplicitIntent.setOnClickListener {
            val phoneNumber = "911"
            val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            startActivity(dialPhoneIntent)
        }
    }
}