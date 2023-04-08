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
    private val signUpStuModel = MutableLiveData<UserModel>()
    private val signUpProfModel = MutableLiveData<UserModel>()
    private val signInModel = MutableLiveData<UserModel>()

    fun getInstance(): AuthRepo {
        instance = AuthRepo()
        return instance
    }

    fun signUpStu(userModel: UserModel) {
        GlobalScope.launch {
            RetroInstance.api.signUpStu(userModel)
                .enqueue(object : Callback<UserModel> {
                    override fun onResponse(
                        call: Call<UserModel>,
                        response: Response<UserModel>
                    ) {
                        signUpStuModel.postValue(response.body())
                        try {
                            message.postValue("OK1")
                        } catch (E: Exception) {
                            message.postValue("Error1")
                        }
                    }

                    override fun onFailure(call: Call<UserModel>, t: Throwable) {
                        message.postValue(t.toString())
                        Log.d("NIK", t.toString())
                    }
                })
        }
    }

    fun signUpProf(userModel: UserModel) {
        GlobalScope.launch {
            RetroInstance.api.signUpProf(userModel)
                .enqueue(object : Callback<UserModel> {
                    override fun onResponse(
                        call: Call<UserModel>,
                        response: Response<UserModel>
                    ) {
                        signUpProfModel.postValue(response.body())
                        try {
                            message.postValue("OK2")
                        } catch (E: Exception) {
                            message.postValue("Error2")
                        }
                    }

                    override fun onFailure(call: Call<UserModel>, t: Throwable) {
                        message.postValue(t.toString())
                        Log.d("NIK", t.toString())
                    }
                })
        }
    }

    fun signIn(userModel: UserModel) {
        GlobalScope.launch {
            RetroInstance.api.signIn(userModel)
                .enqueue(object : Callback<UserModel> {
                    override fun onResponse(
                        call: Call<UserModel>,
                        response: Response<UserModel>
                    ) {
                        signInModel.postValue(response.body())
                        try {
                            message.postValue("OK3")
                        } catch (E: Exception) {
                            message.postValue("Error3")
                        }
                    }

                    override fun onFailure(call: Call<UserModel>, t: Throwable) {
                        message.postValue(t.toString())
                        Log.d("NIK", t.toString())
                    }
                })
        }
    }

    fun getMessage(): MutableLiveData<String> {
        return message
    }
    fun getSignUpStu(): MutableLiveData<UserModel> {
        return signUpStuModel
    }
    fun getSignUpProf(): MutableLiveData<UserModel> {
        return signUpProfModel
    }
    fun getSignIn(): MutableLiveData<UserModel> {
        return signInModel
    }
}