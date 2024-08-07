package com.rohit.bloodconnect.Authentication_Feature.data.Api

import com.rohit.bloodconnect.Authentication_Feature.Request.LoginRequest
import com.rohit.bloodconnect.Authentication_Feature.Request.SignUpRequest
import com.rohit.bloodconnect.Authentication_Feature.domain.entity.User
import com.rohit.bloodconnect.Authentication_Feature.response.AuthResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.POST
import retrofit2.http.Body
interface AuthApi {
    @POST("signup")
    suspend fun signUp(@Body user: SignUpRequest): AuthResponse

    @POST("signin")
    suspend fun signIn(@Body user: LoginRequest): AuthResponse

    @GET("google-signin/{idToken}")
    suspend fun googleSignIn(@Path("idToken") idToken: String): AuthResponse
}