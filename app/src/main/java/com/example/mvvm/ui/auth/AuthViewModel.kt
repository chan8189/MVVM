package com.example.mvvm.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvm.data.repositories.UserRepository

class AuthViewModel : ViewModel() {


    var userId: String? = null
    var password: String? = null
    var grant_type: String? = "password"
    var authListner: AuthListner? = null

    fun onLoginButtonClick(view: View) {
        authListner?.onStarted()

        if (userId.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListner?.onFailure("Invalid UserId or Password")
            return
        }

        val loginResponse = UserRepository().userLogin(userId!!, password!!, grant_type!!)
        authListner?.onSuccess(loginResponse)
    }
}