package com.example.baker_street.repositories

import androidx.lifecycle.MutableLiveData
import com.example.baker_street.RetroInstance
import com.example.baker_street.models.UserModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepo {

    private lateinit var instance: AuthRepo
    private val message = MutableLiveData<String>()

    fun getInstance(): AuthRepo {
        instance = AuthRepo()
        return instance
    }

    fun signIn(userModel : UserModel,jwtToken : String) {
        GlobalScope.launch {
            RetroInstance.api.signUp(userModel, jwtToken)
                .enqueue(object : Callback<UserModel> {
                    override fun onResponse(
                        call: Call<UserModel>,
                        response: Response<UserModel>
                    ) {
                        //try catch
                    }
                    override fun onFailure(call: Call<UserModel>, t: Throwable) {

                    }
                })
        }
    }

    fun getMessage(): MutableLiveData<String> {
        return message
    }
}