package com.rohit.bloodconnect.Authentication_Feature.data.repository

import com.rohit.bloodconnect.Authentication_Feature.Request.LoginRequest
import com.rohit.bloodconnect.Authentication_Feature.Request.SignUpRequest
import com.rohit.bloodconnect.Authentication_Feature.domain.entity.User
import com.rohit.bloodconnect.Authentication_Feature.response.AuthResponse

    interface AuthRepository {
        suspend fun signIn(user: LoginRequest): AuthResponse
        suspend fun signUp(user: SignUpRequest): AuthResponse
        suspend fun googleSignIn(tokenId: String): AuthResponse
    }