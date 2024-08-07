package com.rohit.bloodconnect.Authentication_Feature.response
data class AuthResponse(
    val jwt: String,
    val status: Boolean,
    val message: String
)
