package com.example.baker_street.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.baker_street.adapters.CoursesAdapter
import com.example.baker_street.databinding.FragmentCoursesBinding
import com.example.baker_street.models.CourseModel
import com.example.baker_street.viewmodels.CourseViewModel

class CoursesFragment : Fragment() ,SwipeRefreshLayout.OnRefreshListener,CoursesAdapter.OnCourseClickListener { //TODO : Swipe Refresh Listener to be added
    private lateinit var binding: FragmentCoursesBinding
    private lateinit var adapter: CoursesAdapter
    private lateinit var viewModel: CourseViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoursesBinding.inflate(layoutInflater)
        binding.recyclerView.setHasFixedSize(true)
//        binding.swipeRefresh.setOnRefreshListener(this)

        viewModel = ViewModelProvider(this)[CourseViewModel::class.java]
        viewModel.getCourses("jwtToken from Shared Pref")
        initObservers()
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    private fun initObservers() {
        viewModel.getMessageObserver()?.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            if (it == "OKCourses") {
                viewModel.getCoursesObserver()?.observe(viewLifecycleOwner) {
                    val coursesList: ArrayList<CourseModel>? = it.coursesModel
                    adapter.setList(coursesList)
                }
            }
        }
    }

    override fun onRefresh() {
        viewModel.getCourses("jwtToken from Shared Pref")
    }

    override fun onCourseClick(pos: Int) {

    }
}