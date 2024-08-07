package com.rohit.bloodconnect.Authentication_Feature.Request

data class LoginRequest(
    val email: String,
    val password: String
) {
}