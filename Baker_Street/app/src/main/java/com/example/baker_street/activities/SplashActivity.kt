package com.example.baker_street.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.baker_street.R
import java.util.*


class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT = 800
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }
}