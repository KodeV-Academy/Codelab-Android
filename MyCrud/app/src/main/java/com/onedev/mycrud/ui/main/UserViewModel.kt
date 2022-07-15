package com.onedev.mycrud.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onedev.mycrud.api.remote.ApiConfig
import com.onedev.mycrud.api.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    private val _dataUser = MutableLiveData<List<UserListResponse.Data>>()
    val dataUser = _dataUser

    private val _statusDeleteUser = MutableLiveData<Int>()
    val statusDeleteUser = _statusDeleteUser

    private val _statusEditUser = MutableLiveData<String>()
    val statusEditUser = _statusEditUser

    private val _statusAddUser = MutableLiveData<String>()
    val statusAddUser = _statusAddUser

    fun getUsers() {
        val client = ApiConfig.getApiService().listUser()
        client.enqueue(object : Callback<UserListResponse> {
            override fun onResponse(call: Call<UserListResponse>, response: Response<UserListResponse>) {
                if (response.isSuccessful) {
                    _dataUser.value = response.body()?.data
                } else {
                    _dataUser.value = null
                }
            }

            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                _dataUser.value = null
            }
        })
    }

    fun deleteUser(id: String) {
        val client = ApiConfig.getApiService().deleteUser(id)
        client.enqueue(object : Callback<ResponseDeleteUser> {
            override fun onResponse(call: Call<ResponseDeleteUser>, response: Response<ResponseDeleteUser>) {
                if (response.isSuccessful) {
                    if (response.body()?.data == 1) {
                        _statusDeleteUser.value = response.body()?.data
                    } else {
                        _statusDeleteUser.value = null
                    }
                } else {
                    _statusDeleteUser.value = null
                }
            }

            override fun onFailure(call: Call<ResponseDeleteUser>, t: Throwable) {
                _statusDeleteUser.value = null
            }
        })
    }

    fun editUser(id: String, requestEditUser: RequestEditUser) {
        val client = ApiConfig.getApiService().updateUser(id, requestEditUser)
        client.enqueue(object : Callback<ResponseEditUser> {
            override fun onResponse(call: Call<ResponseEditUser>, response: Response<ResponseEditUser>) {
                if (response.isSuccessful) {
                    _statusEditUser.value = response.body()?.msg
                } else {
                    _statusEditUser.value = null
                }
            }

            override fun onFailure(call: Call<ResponseEditUser>, t: Throwable) {
                _statusEditUser.value = null
            }
        })
    }

    fun addUser(request: RequestAddUser) {
        val client = ApiConfig.getApiService().addUser(request)
        client.enqueue(object : Callback<ResponseAdduUser> {
            override fun onResponse(
                call: Call<ResponseAdduUser>,
                response: Response<ResponseAdduUser>
            ) {
                if (response.isSuccessful) {
                    _statusAddUser.value = response.body()?.msg
                } else {
                    _statusAddUser.value = null
                }
            }

            override fun onFailure(call: Call<ResponseAdduUser>, t: Throwable) {
                _statusAddUser.value = null
            }

        })
    }

}