package com.example.baker_street

import com.example.baker_street.models.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface Service {

    @POST("auth/signup")
    fun signUp(
        @Body userModel: UserModel,
    ): Call<UserModel>

}