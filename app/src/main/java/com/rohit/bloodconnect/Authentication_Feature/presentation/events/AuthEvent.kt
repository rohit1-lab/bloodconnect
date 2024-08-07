package com.rohit.bloodconnect.Authentication_Feature.presentation.events

sealed class AuthEvent {
    data class SignUp(val email: String, val password: String,val fullNAme:String) : AuthEvent()
    data class Login(val email: String, val password: String) : AuthEvent()
    data class GoogleSignIn(val tokenId: String) : AuthEvent()


}