package com.onedev.mycrud.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onedev.mycrud.api.remote.ApiConfig
import com.onedev.mycrud.api.response.Login
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private var _dataLogin = MutableLiveData<Login.Response.Data>()
    val dataLogin: LiveData<Login.Response.Data> = _dataLogin

    fun login(requestLogin: Login.Request) {
        val client = ApiConfig.getApiService().login(requestLogin)
        client.enqueue(object : Callback<Login.Response> {
            override fun onResponse(
                call: Call<Login.Response>,
                response: Response<Login.Response>
            ) {
                if (response.isSuccessful) {
                    _dataLogin.value = response.body()?.data
                } else {
                    _dataLogin.value = null
                }
            }

            override fun onFailure(call: Call<Login.Response>, t: Throwable) {
                _dataLogin.value = null
            }
        })
    }
}