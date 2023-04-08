package com.example.baker_street.models

import com.google.gson.annotations.SerializedName
import java.util.Objects

data class CourseModel(

    val code: String? = null,

    val name: String? = null,

    val profid: String? = null,

    val materials: ArrayList<Any>? = null,
)

data class CoursesModel(

    val status: String? = null,

    @SerializedName("courses")
    val courses: ArrayList<CourseModel>? = null
)