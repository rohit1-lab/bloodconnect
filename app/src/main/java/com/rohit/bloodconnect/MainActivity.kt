package com.rohit.bloodconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.bloodconnect.ui.auth.LoginScreen
import com.google.firebase.messaging.FirebaseMessaging
import com.rohit.bloodconnect.Authentication_Feature.navigation.Screen
import com.rohit.bloodconnect.Authentication_Feature.presentation.composables.*
import com.rohit.bloodconnect.Authentication_Feature.theme.BloodConnectTheme
import com.rohit.bloodconnect.BloodRequest_Feature.Request.UpdateTokenRequest
import com.rohit.bloodconnect.BloodRequest_Feature.data.Repository.UserRepository
import com.rohit.bloodconnect.shared_preference.SharedPreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BloodConnectTheme {


                val navController = rememberNavController()
                val context = LocalContext.current
                val scope = rememberCoroutineScope()

                var startDestination by remember { mutableStateOf(Screen.Splash.route) }

                LaunchedEffect(Unit) {

                    scope.launch {
                        val jwt = SharedPreferencesHelper.getJwtToken(context)
                        val isOnboardingComplete =
                            SharedPreferencesHelper.isOnboardingComplete(context)

                        startDestination = when {
                            jwt.isNullOrEmpty() -> Screen.Login.route
                            !isOnboardingComplete -> Screen.Onboarding.route
                            else -> Screen.Home.route
                        }
                    }


                }

                NavHost(navController = navController, startDestination = startDestination) {
                    composable(Screen.Splash.route) {
                        SplashScreen(navController)
                    }
                    composable(Screen.Login.route) {
                        LoginScreen(navController)
                    }
                    composable(Screen.SignUp.route) {
                        SignupScreen(navController)
                    }
                    composable(Screen.Onboarding.route) {
                        OnboardingScreen(navController)
                    }
                    composable(Screen.Home.route) {
                        HomeScreen(navController)
                    }
                    // Nested navigation for main screens
                    navigation(startDestination = Screen.Home.route, route = "main") {
                        composable(Screen.Home.route) {
                            HomeScreen(navController)
                        }
                        composable(Screen.DonateBlood.route) {
                            DonateBloodScreen(navController)
                        }
                        composable(Screen.Request.route) {
                            RequestScreen(navController)
                        }
                        composable(Screen.MyProfile.route) {
                            MyProfileScreen(navController)
                        }
                    }
                }
                HomeScreen(navController = navController)
            }
        }

    }
}
