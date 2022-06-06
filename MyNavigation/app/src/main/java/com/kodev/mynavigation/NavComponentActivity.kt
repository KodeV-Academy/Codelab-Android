package com.kodev.mynavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.kodev.mynavigation.databinding.ActivityNavComponentBinding

class NavComponentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavComponentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavComponentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.menu_search -> {
//                Toast.makeText(this@NavComponentActivity, "Menu Search", Toast.LENGTH_SHORT).show()
//            }
//            R.id.menu_notification -> {
//                Toast.makeText(this@NavComponentActivity, "Menu Notification", Toast.LENGTH_SHORT).show()
//            }
//            R.id.menu_favorite -> {
//                Toast.makeText(this@NavComponentActivity, "Menu Favorite", Toast.LENGTH_SHORT).show()
//            }
//            R.id.menu_person -> {
//                Toast.makeText(this@NavComponentActivity, "Menu Person", Toast.LENGTH_SHORT).show()
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
}