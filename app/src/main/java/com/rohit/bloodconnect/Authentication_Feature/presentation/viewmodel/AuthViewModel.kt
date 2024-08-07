package com.rohit.bloodconnect.Authentication_Feature.presentation.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.rohit.bloodconnect.Authentication_Feature.Request.LoginRequest
import com.rohit.bloodconnect.Authentication_Feature.Request.SignUpRequest
import com.rohit.bloodconnect.Authentication_Feature.domain.entity.User
import com.rohit.bloodconnect.Authentication_Feature.presentation.events.AuthEvent
import com.rohit.bloodconnect.Authentication_Feature.presentation.states.AuthState
import com.rohit.bloodconnect.Authentication_Feature.usecases.GoogleSignInUseCase
import com.rohit.bloodconnect.Authentication_Feature.usecases.SignInUseCase
import com.rohit.bloodconnect.Authentication_Feature.usecases.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val googleSignInUseCase: GoogleSignInUseCase
) : ViewModel() {
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.Login -> signIn(event.email, event.password)
            is AuthEvent.SignUp -> signUp(event.email,event.password,event.fullNAme)
            is AuthEvent.GoogleSignIn -> googleSignIn(event.tokenId)
        }
    }

    private fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = signInUseCase(LoginRequest(email,password))
                _authState.postValue(AuthState.Success(response))
            } catch (e: Exception) {
                _authState.postValue(AuthState.Error(e.message))
            }
        }
    }

    private fun signUp(email: String, password: String, fullName: String) {
        viewModelScope.launch {
            try {
                val response = signUpUseCase(SignUpRequest(fullName,email,password))
                _authState.postValue(AuthState.Success(response))
            } catch (e: Exception) {
                _authState.postValue(AuthState.Error(e.message))
            }
        }
    }

    private fun googleSignIn(idToken: String) {
        viewModelScope.launch {
            try {
                val response = googleSignInUseCase(idToken)
                _authState.postValue(AuthState.Success(response))
            } catch (e: Exception) {
                _authState.postValue(AuthState.Error(e.message))
            }
        }
    }
}
