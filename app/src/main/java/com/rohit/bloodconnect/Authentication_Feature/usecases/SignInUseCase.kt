package com.rohit.bloodconnect.Authentication_Feature.usecases

import com.rohit.bloodconnect.Authentication_Feature.Request.LoginRequest
import com.rohit.bloodconnect.Authentication_Feature.data.repository.AuthRepository
import com.rohit.bloodconnect.Authentication_Feature.domain.entity.User
import com.rohit.bloodconnect.Authentication_Feature.response.AuthResponse
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(user: LoginRequest): AuthResponse {
        return repository.signIn(user)
    }
}