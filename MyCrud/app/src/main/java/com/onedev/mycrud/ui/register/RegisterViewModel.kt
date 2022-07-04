package com.onedev.mycrud.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onedev.mycrud.api.remote.ApiConfig
import com.onedev.mycrud.api.response.Register
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel: ViewModel() {

    private val _dataRegister = MutableLiveData<Register.Response.Data>()
    val dataRegister: LiveData<Register.Response.Data> = _dataRegister

    fun register(request: Register.Request) {
        val client = ApiConfig.getApiService().register(request)
        client.enqueue(object : Callback<Register.Response> {
            override fun onResponse(
                call: Call<Register.Response>,
                response: Response<Register.Response>
            ) {
                if (response.isSuccessful) {
                    _dataRegister.value = response.body()?.data
                } else {
                    _dataRegister.value = null
                }
            }

            override fun onFailure(call: Call<Register.Response>, t: Throwable) {
                _dataRegister.value = null
            }
        })
    }
}