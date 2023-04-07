package com.example.baker_street.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.baker_street.repositories.AuthRepo

class AuthViewModel {
    var message: MutableLiveData<String>? = null
    var repo: AuthRepo? = null

    init {
        repo = AuthRepo().getInstance()
        message = MutableLiveData<String>()
    }

    fun getMessageObserver(): MutableLiveData<Us>? {
        message = repo?.getMessage()
        return message
    }

    fun signUpUser(email: String, password: String,clicked:Int,context: Context) {
        repo?.authenticate(email, password, clicked ,context)
    }
}