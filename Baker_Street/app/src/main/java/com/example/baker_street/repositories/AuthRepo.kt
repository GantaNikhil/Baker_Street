package com.example.baker_street.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AuthRepo {

    private lateinit var instance: AuthRepo
    private val message = MutableLiveData<ResponseItem>()

    fun getInstance(): AuthRepo {
        instance = AuthRepo()
        return instance
    }

    fun authenticate(email: String, password: String,clicked:Int, context: Context) {
        GlobalScope.launch {
            RetroInstance.api.PostSignUp(SignUpItem(email, password, clicked))
                .enqueue(object : Callback<ResponseItem> {
                    override fun onResponse(
                        call: Call<ResponseItem>,
                        response: Response<ResponseItem>
                    ) {
                        try {
                            val sharedpref =context.getSharedPreferences("token", Context.MODE_PRIVATE)
                            val editor = sharedpref.edit()

                            editor.putString("token", response.body()!!.token)
                            editor.commit()

                            message.postValue(response.body())
                            Log.d("signupmessage", response.body().toString())

//                            response.errorBody()?.let { Log.e("error", it.string()) }
                        } catch (e: Exception) {
//                            message.postValue("100")
                            e.printStackTrace()
                        }

                    }
                    override fun onFailure(call: Call<ResponseItem>, t: Throwable) {
//                        message.postValue(t.message)
                        Log.d("message", t.toString())
                    }
                })
        }
    }

    fun getMessage(): MutableLiveData<ResponseItem> {
        return message;
    }
}