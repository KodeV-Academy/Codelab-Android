package com.kodev.myretrofit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.kodev.myretrofit.adapter.GameAdapter
import com.kodev.myretrofit.databinding.ActivityMainBinding
import com.kodev.myretrofit.model.ResponseGame
import com.kodev.myretrofit.service.ServiceBuilder.apiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var gameAdapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gameAdapter = GameAdapter()
        binding.apply {
            rvGames.adapter = gameAdapter
        }

        val client = apiService.getListGame("d084045ca6164bbeb97021752a930416", "10")
        client.enqueue(object : Callback<ResponseGame> {
            override fun onResponse(call: Call<ResponseGame>, response: Response<ResponseGame>) {
                val listGame = response.body()?.results
                if (listGame != null) {
                    gameAdapter.setListData(listGame)
                }
                Log.d(TAG, "onResponse: $listGame")
            }

            override fun onFailure(call: Call<ResponseGame>, t: Throwable) {
                Log.d(TAG, "onFailure:${t.localizedMessage}")
            }
        })

        binding.btnToUserActivity.setOnClickListener(this)
    }

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnToUserActivity -> {
                startActivity(Intent(this@MainActivity, UserActivity::class.java))
            }
        }
    }
}