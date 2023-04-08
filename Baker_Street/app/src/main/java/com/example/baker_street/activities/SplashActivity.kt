package com.example.baker_street.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.baker_street.R
import java.util.*


class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT = 800
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPreferences = getSharedPreferences("Baker_Street", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("jwtToken","")
//        token?.let { Log.d("NIKHIL", it) }
        if(token != "") {
            val intent = Intent(this@SplashActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Handler().postDelayed({
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
            }, SPLASH_TIME_OUT.toLong())
        }
    }
}