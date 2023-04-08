package com.example.baker_street

import com.example.baker_street.models.CoursesModel
import com.example.baker_street.models.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface Service {

    @POST("auth/signup/students")
    fun signUpStu(
        @Body userModel: UserModel
    ): Call<UserModel>

    @POST("auth/signup/professors")
    fun signUpProf(
        @Body userModel: UserModel
    ): Call<UserModel>

    @POST("auth/signin")
    fun signIn(
        @Body userModel: UserModel
    ): Call<UserModel>

    @POST("courses")
    fun getCourses(
        @Header("Authorization") jwtToken :String
    ): Call<CoursesModel>
}