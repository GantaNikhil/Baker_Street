package com.example.baker_street.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.baker_street.databinding.FragmentStreamBinding

class StreamFragment : Fragment() {
    lateinit var binding: FragmentStreamBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStreamBinding.inflate(layoutInflater)


        return binding.root
    }
}