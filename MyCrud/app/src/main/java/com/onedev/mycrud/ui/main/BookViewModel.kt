package com.onedev.mycrud.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onedev.mycrud.api.remote.ApiConfig
import com.onedev.mycrud.api.response.BooksResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookViewModel() : ViewModel() {

    private val _dataBooks = MutableLiveData<List<BooksResponse.Data>>()
    val dataBooks = _dataBooks

    fun getBooks() {
        val client = ApiConfig.getApiService().getBooks()
        client.enqueue(object : Callback<BooksResponse> {
            override fun onResponse(call: Call<BooksResponse>, response: Response<BooksResponse>) {
                if (response.isSuccessful) {
                    _dataBooks.value = response.body()?.data
                } else {
                    _dataBooks.value = null
                }
            }

            override fun onFailure(call: Call<BooksResponse>, t: Throwable) {
                _dataBooks.value = null
            }
        })
    }

}