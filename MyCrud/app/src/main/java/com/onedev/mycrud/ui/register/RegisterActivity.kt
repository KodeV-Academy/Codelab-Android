package com.onedev.mycrud.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.onedev.mycrud.api.response.Register
import com.onedev.mycrud.databinding.ActivityLoginBinding
import com.onedev.mycrud.databinding.ActivityRegisterBinding
import com.onedev.mycrud.ui.book.BookActivity
import com.onedev.mycrud.ui.login.LoginActivity
import com.onedev.mycrud.utils.Support.showToast

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private val registerViewModel: RegisterViewModel by viewModels()
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener(this)
        binding.tvLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.tvLogin -> {
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            }
            binding.btnRegister -> {
                val name = binding.edtName.text.toString()
                val email = binding.edtEmail.text.toString()
                val password = binding.edtPassword.text.toString()

                if (name.isEmpty()) {
                    applicationContext.showToast("Harap mengisi nama")
                } else if (email.isEmpty()) {
                    applicationContext.showToast("Harap mengisi email")
                } else if (password.isEmpty()) {
                    applicationContext.showToast("Harap mengisi password")
                } else {
                    binding.progressCircular.visibility = View.VISIBLE
                    binding.btnRegister.visibility = View.GONE

                    val register = Register.Request(name, email, password)
                    registerViewModel.register(register)

                    registerViewModel.dataRegister.observe(this@RegisterActivity) { response ->
                        if (response != null) {
                            applicationContext.showToast("Registrasi Berhasil")
                            startActivity(Intent(applicationContext, LoginActivity::class.java))
                        } else {
                            applicationContext.showToast("Registrasi Gagal")
                            binding.progressCircular.visibility = View.GONE
                            binding.btnRegister.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }
}