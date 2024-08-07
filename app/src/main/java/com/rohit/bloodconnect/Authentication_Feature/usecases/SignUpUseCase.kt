package com.rohit.bloodconnect.Authentication_Feature.usecases

import com.rohit.bloodconnect.Authentication_Feature.Request.SignUpRequest
import com.rohit.bloodconnect.Authentication_Feature.data.repository.AuthRepository
import com.rohit.bloodconnect.Authentication_Feature.domain.entity.User
import com.rohit.bloodconnect.Authentication_Feature.response.AuthResponse
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(user: SignUpRequest): AuthResponse {
        return repository.signUp(user)
    }
}