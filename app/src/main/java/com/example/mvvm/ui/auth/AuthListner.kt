package com.example.mvvm.ui.auth

interface AuthListner {

    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)

}