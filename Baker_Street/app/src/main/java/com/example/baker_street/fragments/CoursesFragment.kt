package com.example.baker_street.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
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

class CoursesFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener,
    CoursesAdapter.OnCourseClickListener {
    private lateinit var binding: FragmentCoursesBinding
    private lateinit var adapter: CoursesAdapter
    private lateinit var viewModel: CourseViewModel
    private lateinit var token :String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoursesBinding.inflate(layoutInflater)
        binding.recyclerView.setHasFixedSize(true)
        binding.swipeRefresh.setOnRefreshListener(this)

        viewModel = ViewModelProvider(this)[CourseViewModel::class.java]
        val sharedPreferences = context?.getSharedPreferences("Baker_Street", Context.MODE_PRIVATE)
        token = sharedPreferences?.getString("jwtToken", "").toString()
        viewModel.getCourses(token)
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        viewModel.getMessageObserver()?.observe(viewLifecycleOwner) { message ->
//            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            if (message == "OKCourses") {
                viewModel.getCoursesObserver()?.observe(viewLifecycleOwner) {
                    binding.swipeRefresh.isRefreshing = false
                    if (it == null) {
                        adapter = CoursesAdapter()
                        adapter.setList(ArrayList())
                        binding.recyclerView.adapter = adapter
                    }
                    else
                    {
                        Log.d("sai", it.toString())
                        val coursesList: ArrayList<CourseModel>? = it.courses
                        adapter = CoursesAdapter()
                        adapter.setList(coursesList)
                        binding.recyclerView.adapter = adapter
                    }
                }
            }
        }
    }

    override fun onRefresh() {
        viewModel.getCourses(token)
    }

    override fun onCourseClick(pos: Int) {

    }
}