package com.example.baker_street.activities

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.baker_street.R
import com.example.baker_street.databinding.SubItemAnnouncementsBinding

class SubAnnouncementActivity : AppCompatActivity() {

    private lateinit var binding: SubItemAnnouncementsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SubItemAnnouncementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val announcement = intent.getSerializableExtra("Announcement")
    }
}