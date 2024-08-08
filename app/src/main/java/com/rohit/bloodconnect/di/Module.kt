package com.rohit.bloodconnect.di

import android.app.Application
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.rohit.bloodconnect.Authentication_Feature.data.Api.AuthApi
import com.rohit.bloodconnect.Authentication_Feature.data.repository.AuthRepository
import com.rohit.bloodconnect.Authentication_Feature.data.repository.AuthRepositoryImpl
import com.rohit.bloodconnect.Authentication_Feature.usecases.GoogleSignInUseCase
import com.rohit.bloodconnect.Authentication_Feature.usecases.SignInUseCase
import com.rohit.bloodconnect.Authentication_Feature.usecases.SignUpUseCase
import com.rohit.bloodconnect.BloodRequest_Feature.data.Api.UserApi
import com.rohit.bloodconnect.BloodRequest_Feature.data.Repository.UserRepository
import com.rohit.bloodconnect.BloodRequest_Feature.data.Repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()



    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://your.api.base.url/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserRepository {
        return retrofit.create(UserRepository::class.java)
    }
    @Provides
    @Singleton
    fun provideUserRepository(userApi: UserApi): UserRepository = UserRepositoryImpl(userApi)
    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AuthApi): AuthRepository = AuthRepositoryImpl(authApi)
    @Provides
    @Singleton
    fun provideLoginUseCase(authRepository: AuthRepository) = SignInUseCase(authRepository)

    @Provides
    @Singleton
    fun provideSignupUseCase(authRepository: AuthRepository) = SignUpUseCase(authRepository)

    @Provides
    @Singleton
    fun provideGoogleSignInUseCase(authRepository: AuthRepository) = GoogleSignInUseCase(authRepository)
}
