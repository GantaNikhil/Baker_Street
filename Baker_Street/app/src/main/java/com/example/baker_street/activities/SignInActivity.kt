package com.example.baker_street.activities

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class SignInActivity : AppCompatActivity() {

    val signupviewmodel = ViewModelProvider(this)[SignUpViewModel::class.java]

    signupviewmodel.getMessageObserver()?.observe(this,
    object : Observer<ResponseItem?> {
        override fun onChanged(it: ResponseItem?) {
            if (it?.status == "201") {
                Toast.makeText(
                    this@SignUpActivity,
                    "Successfully Registered",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                overridePendingTransition(R.anim.from_left, R.anim.to_right)
                finish()
            } else if (it == "100") {
                Toast.makeText(
                    this@SignUpActivity,
                    "User already exists!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this@SignUpActivity,
                    "Check your Internet Connection!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            dialog.dismiss()
        }
    })

    signup.setOnClickListener(View.OnClickListener

    })
}