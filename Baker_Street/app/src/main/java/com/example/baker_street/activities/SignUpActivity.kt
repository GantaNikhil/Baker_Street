package com.example.baker_street.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.baker_street.R
import com.example.baker_street.databinding.ActivitySignupBinding
import com.example.baker_street.models.UserModel
import com.example.baker_street.viewmodels.AuthViewModel

class SignUpActivity :AppCompatActivity(){

    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var cnfpassword:EditText
    private lateinit var signupviewmodel:AuthViewModel
    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

         email = binding.txtEEmail
         password = binding.txtEPassword
         cnfpassword = binding.txtEConfirmPassword

         signupviewmodel = ViewModelProvider(this)[AuthViewModel::class.java]

        binding.btnSignUp.setOnClickListener {
            signupviewmodel.signUpUser(UserModel(email.text.toString(), password.text.toString(),cnfpassword.text.toString()),"")
        }

        binding.goToLogin.setOnClickListener{
            val toSignIn = Intent(this,SignInActivity::class.java)
            startActivity(toSignIn)
        }

        signupviewmodel.getMessageObserver()?.observe(this){
            Log.d("NIK",it.toString())
        }

    }
}