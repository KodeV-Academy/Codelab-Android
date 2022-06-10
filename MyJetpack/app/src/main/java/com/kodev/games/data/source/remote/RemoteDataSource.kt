package com.kodev.games.data.source.remote

import android.util.Log
import com.kodev.games.data.source.remote.api.ApiConfig.getApiService
import com.kodev.games.data.source.remote.response.ResponseGame
import com.kodev.games.utils.JsonHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource() {

    companion object {
        private const val TAG = "RemoteDataSource"
    }

    fun getGames(callback: LoadGetGames) {
        val client = getApiService().getGames("d084045ca6164bbeb97021752a930416", "10")
        client.enqueue(object : Callback<ResponseGame> {
            override fun onResponse(call: Call<ResponseGame>, response: Response<ResponseGame>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback.onLoadGetGames(it)
                    }
                } else {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseGame>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }
        })
    }

    interface LoadGetGames {
        fun onLoadGetGames(listGame: ResponseGame)
    }
}