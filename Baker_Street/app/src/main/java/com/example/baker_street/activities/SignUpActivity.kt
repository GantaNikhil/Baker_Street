package com.example.baker_street.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.baker_street.R
import com.example.baker_street.databinding.ActivitySignupBinding
import com.example.baker_street.models.UserModel
import com.example.baker_street.viewmodels.AuthViewModel

class SignUpActivity :AppCompatActivity(){
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = binding.txtEEmail
        val password = binding.txtEPassword
        val cnfpassword = binding.txtEConfirmPassword
        val signupviewmodel = ViewModelProvider(this)[AuthViewModel::class.java]

        binding.btnSignUp.setOnClickListener {
            signupviewmodel.signUpUser(UserModel(email.text.toString(), password.text.toString(),cnfpassword.text.toString()),"")
        }

        signupviewmodel.getMessageObserver()?.observe(this){
            Log.d("NIK",it.toString())
        }

    }
}