package com.example.baker_street.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baker_street.models.UserModel
import com.example.baker_street.repositories.AuthRepo

class AuthViewModel : ViewModel(){
    private var message: MutableLiveData<String>? = null
    private var signUpStu: MutableLiveData<UserModel>? = null
    private var signUpProf: MutableLiveData<UserModel>? = null
    private var signIn: MutableLiveData<UserModel>? = null
    private var repo: AuthRepo? = null

    init {
        repo = AuthRepo().getInstance()
        message = MutableLiveData<String>()
    }
    fun signUpStu(userModel: UserModel) {
        repo?.signUpStu(userModel)
    }
    fun signUpProf(userModel: UserModel) {
        repo?.signUpProf(userModel)
    }
    fun signIn(userModel: UserModel) {
        repo?.signIn(userModel)
    }
    fun getMessageObserver(): MutableLiveData<String>? {
        message = repo?.getMessage()
        return message
    }
    fun getSignUpStuObserver(): MutableLiveData<UserModel>? {
        signUpStu = repo?.getSignUpStu()
        return signUpStu
    }
    fun getSignUpProfObserver(): MutableLiveData<UserModel>? {
        signUpProf = repo?.getSignUpProf()
        return signUpProf
    }
    fun getSignInObserver(): MutableLiveData<UserModel>? {
        signIn = repo?.getSignIn()
        return signIn
    }
}