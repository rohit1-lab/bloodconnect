package com.rohit.bloodconnect.Authentication_Feature.usecases

import com.rohit.bloodconnect.Authentication_Feature.data.repository.AuthRepository
import com.rohit.bloodconnect.Authentication_Feature.presentation.events.AuthEvent
import com.rohit.bloodconnect.Authentication_Feature.response.AuthResponse
import javax.inject.Inject

class GoogleSignInUseCase @Inject constructor(

    private val repository: AuthRepository) {
    suspend operator fun invoke(token:String): AuthResponse {


        return repository.googleSignIn(token)
    }
}