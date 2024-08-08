package com.rohit.bloodconnect.Authentication_Feature.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.rohit.bloodconnect.R
import com.rohit.bloodconnect.Authentication_Feature.navigation.Screen
import com.rohit.bloodconnect.util.JwtUtils
import com.rohit.bloodconnect.shared_preference.SharedPreferencesHelper
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) { val context = LocalContext.current

      Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
Image(painter = painterResource(id = R.drawable.untitled_design__3_), contentDescription = null)
         }
    LaunchedEffect(Unit) {
        delay(2000) // Show splash screen for 2 seconds
        val isOnboardingComplete = SharedPreferencesHelper.isOnboardingComplete(context)
        val jwtToken = SharedPreferencesHelper.getJwtToken(context)

        when {
            !isOnboardingComplete -> {
                navController.navigate(Screen.Onboarding.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }
            jwtToken.isNullOrEmpty() || JwtUtils.isTokenExpired(jwtToken) -> {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }
            else -> {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }
        }
    }
}