package com.example.projectjusandeposit

sealed class AppState{
    object Success:AppState()
    object Delete:AppState()
    object Error:AppState()
}

