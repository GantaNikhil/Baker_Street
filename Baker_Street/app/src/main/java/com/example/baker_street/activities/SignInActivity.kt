package com.example.baker_street.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.baker_street.R
import com.example.baker_street.models.UserModel
import com.example.baker_street.viewmodels.AuthViewModel

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val signinviewmodel = ViewModelProvider(this)[AuthViewModel::class.java]

        signinviewmodel.getMessageObserver()?.observe(this){

        }

//        signup.setOnClickListener{
//
//        }
    }
}