package com.rohit.bloodconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bloodconnect.ui.auth.LoginScreen
import com.rohit.bloodconnect.Authentication_Feature.navigation.Screen
import com.rohit.bloodconnect.Authentication_Feature.presentation.composables.HomeScreen
import com.rohit.bloodconnect.Authentication_Feature.presentation.composables.OnboardingScreen
import com.rohit.bloodconnect.Authentication_Feature.presentation.composables.SignupScreen
import com.rohit.bloodconnect.Authentication_Feature.presentation.composables.SplashScreen
import com.rohit.bloodconnect.Authentication_Feature.presentation.viewmodel.AuthViewModel
import com.rohit.bloodconnect.Authentication_Feature.theme.BloodConnectTheme
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BloodConnectTheme {
                navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Splash.route
                ) {
                    composable(Screen.Splash.route) {
                        SplashScreen(navController)
                    }
                    composable(Screen.Onboarding.route) {
                        OnboardingScreen(navController)
                    }
                    composable(Screen.Login.route) {
                        LoginScreen(navController,)
                    }
                    composable(Screen.SignUp.route) {
                        SignupScreen(navController)
                    }
                    composable(Screen.Home.route) {
                        HomeScreen(navController)
                    }
                }
            }
        }
    }
}
