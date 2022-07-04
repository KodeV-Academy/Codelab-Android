package com.onedev.mycrud.utils

import android.content.Context
import android.widget.Toast

object Support {
    fun Context.showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}