package com.example.baker_street.models

data class CourseModel(

    val coursecode: String? = null,

    val coursename: String? = null,

    val profname: String? = null,


)

data class CoursesModel(

    val status: String? = null,

    val coursesModel: ArrayList<CourseModel>? = null

)