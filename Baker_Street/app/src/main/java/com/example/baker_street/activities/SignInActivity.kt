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

        signinviewmodel.getMessageObserver()?.observe(this,
            object : Observer<UserModel?> {
                override fun onChanged(it: UserModel?) {
                    if (it?.status == "201") {
                        Toast.makeText(
                            this@SignInActivity,
                            "Successfully Registered",
                            Toast.LENGTH_SHORT
                        ).show()

                        val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                        finish()
                    } else if (it == "100") {
                        Toast.makeText(
                            this@SignInActivity,
                            "User already exists!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@SignInActivity,
                            "Check your Internet Connection!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })

        signup.setOnClickListener{

        }
    }
}