package com.rohit.bloodconnect.Authentication_Feature.data.repository

import android.content.Context
import com.rohit.bloodconnect.Authentication_Feature.Request.LoginRequest
import com.rohit.bloodconnect.Authentication_Feature.Request.SignUpRequest
import com.rohit.bloodconnect.Authentication_Feature.data.Api.AuthApi
import com.rohit.bloodconnect.Authentication_Feature.domain.entity.User
import com.rohit.bloodconnect.Authentication_Feature.response.AuthResponse
import dagger.hilt.android.qualifiers.ApplicationContext

class AuthRepositoryImpl(private val authApi: AuthApi,

) : AuthRepository {

    override suspend fun signIn(user: LoginRequest): AuthResponse {
        // Pass User object to the API
        return authApi.signIn(user)
    }

    override suspend fun signUp(user: SignUpRequest): AuthResponse {
        // Pass User object to the API
        return authApi.signUp(user)
    }

    override suspend fun googleSignIn(tokenId: String): AuthResponse {
        // Pass User object to the API

        return authApi.googleSignIn(tokenId)
    }
}
