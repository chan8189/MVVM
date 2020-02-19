package com.example.mvvm.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {


    var userId: String? = null
    var password: String? = null
    var authListner: AuthListner? = null

    fun onLoginButtonClick(view: View) {
        authListner?.onStarted()

        if (userId.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListner?.onFailure("Invalid UserId or Password")
            return
        }
        authListner?.onSuccess()


    }
}