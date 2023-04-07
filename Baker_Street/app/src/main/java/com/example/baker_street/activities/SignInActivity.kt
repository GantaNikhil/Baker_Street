package com.example.baker_street.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.baker_street.R
import com.example.baker_street.viewmodels.AuthViewModel

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val signinviewmodel = ViewModelProvider(this)[AuthViewModel::class.java]

        signinviewmodel.getMessageObserver()?.observe(this) {

        }

        signup.setOnClickListener{

        }
    }
}