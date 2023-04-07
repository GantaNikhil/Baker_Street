package com.example.baker_street.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baker_street.models.UserModel
import com.example.baker_street.repositories.AuthRepo

class AuthViewModel : ViewModel(){
    private var message: MutableLiveData<String>? = null
    private var repo: AuthRepo? = null

    init {
        repo = AuthRepo().getInstance()
        message = MutableLiveData<String>()
    }

    fun getMessageObserver(): MutableLiveData<String>? {
        message = repo?.getMessage()
        return message
    }

    fun signUpUser(userModel: UserModel,jwtToken : String) {
        repo?.signUp(userModel, jwtToken)
    }
}