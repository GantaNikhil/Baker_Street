package com.example.baker_street.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.baker_street.databinding.FragmentCoursesBinding
import com.example.baker_street.databinding.FragmentProfileBinding

class CoursesFragment : Fragment(){
    private lateinit var binding: FragmentCoursesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoursesBinding.inflate(layoutInflater)

        return binding.root
    }
}