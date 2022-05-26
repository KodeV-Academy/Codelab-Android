package com.kodev.myretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.kodev.myretrofit.adapter.UserAdapter
import com.kodev.myretrofit.databinding.ActivityUserBinding
import com.kodev.myretrofit.model.ResponseAddUser
import com.kodev.myretrofit.model.ResponseUser
import com.kodev.myretrofit.service.ServiceBuilder.apiServiceReqres
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userAdapter = UserAdapter()
        binding.rvUser.adapter = userAdapter

        val client = apiServiceReqres.getListUser()
        client.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                val listUser = response.body()?.data
                if (listUser != null) {
                    userAdapter.setListData(listUser)
                    binding.rvUser.visibility = View.VISIBLE
                    binding.progressCircular.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        binding.btnAdd.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnAdd -> {
                val name = binding.edtName.text.toString()
                val job = binding.edtJob.text.toString()
                when {
                    name.isEmpty() -> {
                        showToast("Nama Tidak Boleh Kosong")
                    }
                    job.isEmpty() -> {
                        showToast("Job Tidak Boleh Kosong")
                    }
                    name.length < 5 -> {
                        showToast("Nama Kurang Dari 5 Huruf")
                    }
                    else -> {
                        val client = apiServiceReqres.addUser(name, job)
                        client.enqueue(object : Callback<ResponseAddUser> {
                            override fun onResponse(call: Call<ResponseAddUser>, response: Response<ResponseAddUser>) {
                                if (response.isSuccessful) {
                                    showToast("Data Berhasil Ditambahkan")
                                }
                            }

                            override fun onFailure(call: Call<ResponseAddUser>, t: Throwable) {
                                showToast("Data Gagal Ditambahkan Karena ${t.localizedMessage}")
                            }
                        })
                    }
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@UserActivity, message, Toast.LENGTH_SHORT).show()
    }
}