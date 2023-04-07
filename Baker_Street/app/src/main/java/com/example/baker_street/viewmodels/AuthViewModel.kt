package com.example.baker_street.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baker_street.models.UserModel
import com.example.baker_street.repositories.AuthRepo

class AuthViewModel : ViewModel(){
    var message: MutableLiveData<String>? = null
    var repo: AuthRepo? = null

    init {
        repo = AuthRepo().getInstance()
        message = MutableLiveData<String>()
    }

    fun getMessageObserver(): MutableLiveData<UserModel>? {
        message = repo?.getMessage()
        return message
    }

    fun signInUser(name :String,email: String, password: String) {
        repo?.authenticate(email, password)
    }
}