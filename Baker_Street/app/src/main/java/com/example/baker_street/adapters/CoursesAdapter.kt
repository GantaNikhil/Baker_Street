package com.example.baker_street.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.baker_street.databinding.ListItemCourseBinding
import com.example.baker_street.models.CourseModel

class CoursesAdapter : RecyclerView.Adapter<CoursesAdapter.MyViewHolder>() {

    var data = ArrayList<CourseModel>()

    inner class MyViewHolder(val binding: ListItemCourseBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setList(courseModel: ArrayList<CourseModel>?) {
        this.data.clear()
        if (courseModel != null) {
            this.data.addAll(courseModel)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ListItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.courseName.text = data[position].coursename
        holder.binding.courseProf.text = data[position].profname
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnCourseClickListener {
        fun onCourseClick(pos: Int)
    }
}