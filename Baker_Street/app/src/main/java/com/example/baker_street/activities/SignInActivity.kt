package com.example.baker_street.activities

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.baker_street.R
import com.example.baker_street.databinding.ActivitySigninBinding
import com.example.baker_street.databinding.ActivitySignupBinding
import com.example.baker_street.models.UserModel
import com.example.baker_street.viewmodels.AuthViewModel

class SignInActivity : AppCompatActivity() {


    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var cnfpassword: EditText
    private lateinit var binding:ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val signinviewmodel = ViewModelProvider(this)[AuthViewModel::class.java]

        signinviewmodel.getMessageObserver()?.observe(this){

        }

        binding.txtVForgotPassword.setOnClickListener{
            val forgotPassword = Intent(this,ForgotPasswordActivity::class.java)
            startActivity(forgotPassword)
        }

        binding.txtVSignUp.setOnClickListener{
            val toSignUp = Intent(this,SignUpActivity::class.java)
            startActivity(toSignUp)
        }
//        signup.setOnClickListener{
//
//        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}