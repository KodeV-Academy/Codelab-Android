package com.onedev.androidfundamental.recylerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.onedev.androidfundamental.R
import com.onedev.androidfundamental.databinding.ActivityDetailDoctorBinding

class DetailDoctorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailDoctorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        populateData()
    }

    private fun populateData() {
        val data = intent.getParcelableExtra<Data.Doctor>(EXTRA_DATA)
        if (data != null) {
            Glide.with(binding.imgDoctor)
                .load(data.image)
                .into(binding.imgDoctor)
            binding.tvName.text =  data.name
            binding.tvSpecialist.text = data.specialist
            binding.tvExperience.text = data.experience
            binding.tvPrice.text = data.price
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}