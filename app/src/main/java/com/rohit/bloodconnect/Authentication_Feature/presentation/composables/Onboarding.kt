package com.rohit.bloodconnect.Authentication_Feature.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rohit.bloodconnect.Authentication_Feature.navigation.setFirstTimeUser


import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.rohit.bloodconnect.Authentication_Feature.navigation.Screen
import com.rohit.bloodconnect.shared_preference.SharedPreferencesHelper.setOnboardingComplete

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    var currentPage by remember { mutableStateOf(0) }
    val pages = listOf("Onboarding 1", "Onboarding 2")
val context:Context= LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(pages[currentPage])
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Button(onClick = {
                if (currentPage < pages.size - 1) {
                    currentPage++
                } else {
                    setOnboardingComplete(context, true) // Set onboarding as complete

                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            }) {
                Text(if (currentPage < pages.size - 1) "Next" else "Finish")
            }
        }
    }
}