package com.example.baker_street.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.baker_street.R


class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT = 800
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.baker_street.R.layout.activity_splash)

        val circle = findViewById<ImageView>(R.id.circle)
        val animation = AnimationUtils.loadAnimation(this, R.anim.circle_animation)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000)

        circle.startAnimation(animation)

    }
}

//circle = findViewById<ImageView>(com.example.baker_street.R.id.circle)
//val animation: Animation = AnimationUtils.loadAnimation(this, com.example.baker_street.R.anim.circle_animation)
//circle.startAnimation(animation)
//Handler().postDelayed({
//    startActivity(Intent(this, SignInActivity::class.java))
//    finish()
//}, SPLASH_TIME_OUT.toLong())