package com.onedev.mycrud.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.onedev.mycrud.api.response.Login
import com.onedev.mycrud.databinding.ActivityLoginBinding
import com.onedev.mycrud.ui.book.BookActivity

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnLogin -> {
                val email = binding.edtEmail.text.toString()
                val password = binding.edtPassword.text.toString()

                if (email.isEmpty()) {
                    Toast.makeText(applicationContext, "Harap Mengisi Email", Toast.LENGTH_SHORT).show()
                } else if (password.isEmpty()) {
                    Toast.makeText(applicationContext, "Harap Mengisi Password", Toast.LENGTH_SHORT).show()
                } else {
                    binding.progressCircular.visibility = View.VISIBLE
                    binding.btnLogin.visibility = View.GONE

                    val requestLogin = Login.RequestLogin(email, password)
                    loginViewModel.login(requestLogin)

                    loginViewModel.dataLogin.observe(this) { response ->
                        if (response != null) {
                            Toast.makeText(applicationContext, "Login Berhasil", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(applicationContext, BookActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(applicationContext, "Login Gagal", Toast.LENGTH_SHORT).show()
                            binding.progressCircular.visibility = View.GONE
                            binding.btnLogin.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }
}