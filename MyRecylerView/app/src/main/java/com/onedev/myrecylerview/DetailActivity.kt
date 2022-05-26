package com.onedev.myrecylerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imgDoctor: ImageView = findViewById(R.id.img_doctor)
        val tvNameDoctor: TextView = findViewById(R.id.tv_name_doctor)
        val tvSpecialistDoctor: TextView = findViewById(R.id.tv_specialist_doctor)
        val tvExperienceDoctor: TextView = findViewById(R.id.tv_experience_doctor)
        val tvPriceDoctor: TextView = findViewById(R.id.tv_price_doctor)

        val data = intent.getParcelableExtra<Data.Doctor>(EXTRA_DATA)

        Glide.with(imgDoctor)
            .load(data?.image)
            .into(imgDoctor)

        tvNameDoctor.text = data?.name
        tvSpecialistDoctor.text = data?.specialist
        tvExperienceDoctor.text = data?.experience
        tvPriceDoctor.text = data?.price
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}