package com.yourcompany.gpswoxtracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yourcompany.gpswoxtracker.ui.auth.LoginActivity
import com.yourcompany.gpswoxtracker.ui.main.VehicleListActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val apiKey = sharedPref.getString("api_key", "")

        val destination = if (apiKey.isNullOrEmpty()) {
            LoginActivity::class.java
        } else {
            VehicleListActivity::class.java
        }

        startActivity(Intent(this, destination).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
        finish()
    }
}