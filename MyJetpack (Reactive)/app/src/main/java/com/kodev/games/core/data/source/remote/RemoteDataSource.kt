package com.kodev.games.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kodev.games.core.data.source.remote.api.ApiConfig.getApiService
import com.kodev.games.core.data.source.remote.response.ResponseGame
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource() {

    companion object {
        private const val TAG = "RemoteDataSource"
    }

    fun getGames(): LiveData<ApiResponse<ResponseGame>> {
        val responseGame = MutableLiveData<ApiResponse<ResponseGame>>()
        val client = getApiService().getGames("d084045ca6164bbeb97021752a930416", "20")
        client.enqueue(object : Callback<ResponseGame> {
            override fun onResponse(call: Call<ResponseGame>, response: Response<ResponseGame>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        responseGame.value = ApiResponse.Success(it)
                    }
                } else {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseGame>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }
        })
        return responseGame
    }
}