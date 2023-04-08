package com.example.baker_street.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.baker_street.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    //showFragment()
                    true
                }
                R.id.navigation_profile -> {
                    //showFragment()
                    true
                }
                else -> false
            }
        }
    }
}