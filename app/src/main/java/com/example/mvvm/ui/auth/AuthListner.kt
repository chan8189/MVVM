package com.example.mvvm.ui.auth

import androidx.lifecycle.LiveData

interface AuthListner {

    fun onStarted()
    fun onSuccess(loginResponse: LiveData<String>)
    fun onFailure(message: String)

}