package com.onedev.myrecylerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var doctorAdapter: DoctorAdapter
    private lateinit var rvDoctor: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doctorAdapter = DoctorAdapter()
        rvDoctor = findViewById(R.id.rv_doctor)

        doctorAdapter.setListData(Data.generateDataDoctor())

        rvDoctor.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL,false)
        rvDoctor.adapter = doctorAdapter

        doctorAdapter.onItemClick = {
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, it)
            startActivity(intent)
        }
    }
}