package com.onedev.mycrud.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onedev.mycrud.api.remote.ApiConfig
import com.onedev.mycrud.api.response.RequestLogin
import com.onedev.mycrud.api.response.ResponseLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private var _dataLogin = MutableLiveData<ResponseLogin>()
    val dataLogin: LiveData<ResponseLogin> = _dataLogin

    fun login(requestLogin: RequestLogin) {
        val client = ApiConfig.getApiService().login(requestLogin)
        client.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(
                call: Call<ResponseLogin>,
                response: Response<ResponseLogin>
            ) {
                if (response.isSuccessful) {
                    _dataLogin.value = response.body()
                } else {
                    _dataLogin.value = null
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                _dataLogin.value = null
            }
        })
    }
}