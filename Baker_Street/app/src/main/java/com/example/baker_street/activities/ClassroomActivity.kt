package com.example.baker_street.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.baker_street.R
import com.example.baker_street.databinding.ActivityClassroomBinding
import com.example.baker_street.databinding.ActivityForgotpasswordBinding

class ClassroomActivity : AppCompatActivity() {

    lateinit var binding : ActivityClassroomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClassroomBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}