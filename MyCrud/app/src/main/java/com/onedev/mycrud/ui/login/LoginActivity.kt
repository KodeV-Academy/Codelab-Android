package com.onedev.mycrud.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.onedev.mycrud.api.response.Login
import com.onedev.mycrud.databinding.ActivityLoginBinding
import com.onedev.mycrud.ui.book.BookActivity
import com.onedev.mycrud.ui.register.RegisterActivity
import com.onedev.mycrud.utils.Constant
import com.onedev.mycrud.utils.Support.putBooleanPreference
import com.onedev.mycrud.utils.Support.putStringPreference
import com.onedev.mycrud.utils.Support.showToast

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(this)
        binding.tvRegister.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.tvRegister -> {
                startActivity(Intent(applicationContext, RegisterActivity::class.java))
            }

            binding.btnLogin -> {
                val email = binding.edtEmail.text.toString()
                val password = binding.edtPassword.text.toString()

                if (email.isEmpty()) {
                    applicationContext.showToast("Harap Mengisi Email")
                } else if (password.isEmpty()) {
                    applicationContext.showToast("Harap Mengisi Password")
                } else {
                    binding.progressCircular.visibility = View.VISIBLE
                    binding.btnLogin.visibility = View.GONE

                    val requestLogin = Login.Request(email, password)
                    loginViewModel.login(requestLogin)

                    loginViewModel.dataLogin.observe(this) { response ->
                        if (response != null) {
                            applicationContext.showToast("Login Berhasil")
                            putStringPreference(applicationContext, Constant.TOKEN, response.id)
                            putBooleanPreference(applicationContext, Constant.IS_LOGIN, true)
                            startActivity(Intent(applicationContext, BookActivity::class.java))
                            finish()
                        } else {
                            applicationContext.showToast("Login Gagal")
                            binding.progressCircular.visibility = View.GONE
                            binding.btnLogin.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }
}