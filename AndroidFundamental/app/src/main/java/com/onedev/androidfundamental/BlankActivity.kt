package com.onedev.androidfundamental

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BlankActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blank)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame_container, BlankFragment())
            .commit()
    }
}