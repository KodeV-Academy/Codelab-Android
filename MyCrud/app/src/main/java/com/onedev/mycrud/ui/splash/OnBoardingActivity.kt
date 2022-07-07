package com.onedev.mycrud.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.onedev.mycrud.databinding.ActivityOnBoardingBinding
import com.onedev.mycrud.ui.book.BookActivity
import com.onedev.mycrud.ui.login.LoginActivity
import com.onedev.mycrud.utils.Constant
import com.onedev.mycrud.utils.Support

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = Support.getStringPreference(applicationContext, Constant.TOKEN)

        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                if (userId != "")
                    startActivity(Intent(this@OnBoardingActivity, BookActivity::class.java))
                else
                    startActivity(Intent(this@OnBoardingActivity, LoginActivity::class.java))
            }, 3000)
        }
    }
}