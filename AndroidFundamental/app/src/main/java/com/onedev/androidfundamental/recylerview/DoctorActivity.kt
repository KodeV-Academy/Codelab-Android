package com.onedev.androidfundamental.recylerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onedev.androidfundamental.databinding.ActivityDetailBinding
import com.onedev.androidfundamental.databinding.ActivityDoctorBinding

class DoctorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDoctorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val doctorAdapter = DoctorAdapter()
        doctorAdapter.setListData(Data.generateDataDoctor())
        binding.rvDoctor.adapter = doctorAdapter

        doctorAdapter.onItemClick = {
            val intent = Intent(applicationContext, DetailDoctorActivity::class.java)
            intent.putExtra(DetailDoctorActivity.EXTRA_DATA, it)
            startActivity(intent)
        }
    }
}