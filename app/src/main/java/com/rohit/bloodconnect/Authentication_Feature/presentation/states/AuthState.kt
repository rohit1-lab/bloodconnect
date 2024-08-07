package com.rohit.bloodconnect.Authentication_Feature.presentation.states

import com.rohit.bloodconnect.Authentication_Feature.response.AuthResponse

sealed class AuthState {
    object Idle : AuthState()
     object Loading : AuthState()
    object Authenticated:AuthState()
    object Unauthenticated:AuthState()
    data class Success(val data : AuthResponse) : AuthState()
    data class Error(val error: String?) : AuthState()
}