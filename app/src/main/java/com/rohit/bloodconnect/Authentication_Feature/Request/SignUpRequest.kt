package com.rohit.bloodconnect.Authentication_Feature.Request


data class SignUpRequest(
    val username:String,
    val email: String,
    val password: String
) {
}