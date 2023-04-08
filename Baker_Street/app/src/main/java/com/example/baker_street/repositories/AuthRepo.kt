package com.example.baker_street.repositories

import android.util.Log
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
    fun signUp(userModel : UserModel,jwtToken : String) {
        GlobalScope.launch {
            RetroInstance.api.signUp(userModel)
                .enqueue(object : Callback<UserModel> {
                    override fun onResponse(
                        call: Call<UserModel>,
                        response: Response<UserModel>
                    ) {
                        try {
                            Log.d("NIK","OK")

                        } catch (E :Exception)
                        {
                            Log.d("NIK","Error")
                        }
                    }
                    override fun onFailure(call: Call<UserModel>, t: Throwable) {
                        Log.d("NIK",t.toString())
                    }
                })
        }
    }

    fun getMessage(): MutableLiveData<String> {
        return message
    }
}