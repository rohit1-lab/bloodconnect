package com.rohit.bloodconnect.Authentication_Feature.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.rohit.bloodconnect.Authentication_Feature.navigation.Screen

sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    object Home : BottomNavItem(Screen.Home.route, Icons.Default.Home, "Home")
    object Request : BottomNavItem(Screen.Request.route, Icons.Default.Search, "Request")
    object Profile : BottomNavItem(Screen.MyProfile.route , Icons.Default.Person, "Profile")
}
