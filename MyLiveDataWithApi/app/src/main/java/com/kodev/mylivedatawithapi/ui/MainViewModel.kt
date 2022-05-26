package com.kodev.mylivedatawithapi.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kodev.mylivedatawithapi.model.DataCustomerReview
import com.kodev.mylivedatawithapi.model.DataRestaurant
import com.kodev.mylivedatawithapi.model.RestaurantResponse
import com.kodev.mylivedatawithapi.model.ReviewResponse
import com.kodev.mylivedatawithapi.service.ApiConfig.getApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _restaurant = MutableLiveData<DataRestaurant>()
    val restaurant: LiveData<DataRestaurant> = _restaurant

    private val _listReview = MutableLiveData<List<DataCustomerReview>>()
    val listReview: LiveData<List<DataCustomerReview>> = _listReview

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }

    init {
        findRestaurant()
    }

    fun findRestaurant() {
        _isLoading.value = true
        val client = getApiService().getRestaurant(RESTAURANT_ID)
        client.enqueue(object : Callback<RestaurantResponse> {
            override fun onResponse(
                call: Call<RestaurantResponse>,
                response: Response<RestaurantResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _restaurant.value = response.body()?.restaurant
                    _listReview.value = response.body()?.restaurant?.customerReviews
                } else {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }
        })
    }

    fun postReview(review: String) {
        _isLoading.value = true
        val client = getApiService().postReview(RESTAURANT_ID, "KodeV", review)
        client.enqueue(object : Callback<ReviewResponse> {
            override fun onResponse(
                call: Call<ReviewResponse>,
                response: Response<ReviewResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listReview.value = response.body()?.customerReviews
                } else {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }
        })
    }

}