package com.example.baker_street.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.baker_street.fragments.SignUpFragment

class ClassroomPagerAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        val fragment: Fragment = when (position) {
            0 -> {
                SignUpFragment.newInstance("STUDENT")
            }
            1 -> {
                SignUpFragment.newInstance("PROFESSOR")
            }
            2 -> {
                SignUpFragment.newInstance("PROFESSOR")
            }
            else -> {
                SignUpFragment.newInstance("PROFESSOR")
            }
        }
        return fragment
    }
}