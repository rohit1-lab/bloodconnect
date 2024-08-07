package com.rohit.bloodconnect.Authentication_Feature.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rohit.bloodconnect.Authentication_Feature.navigation.Screen
import com.rohit.bloodconnect.Authentication_Feature.presentation.components.BottomNavItem
import com.rohit.bloodconnect.Authentication_Feature.presentation.components.BottomNavigationBar
import com.rohit.bloodconnect.Authentication_Feature.presentation.components.DrawerContent
import com.rohit.bloodconnect.Authentication_Feature.presentation.components.HomeScreenTopBar
import com.rohit.bloodconnect.Authentication_Feature.presentation.components.MainTopBar
import com.rohit.bloodconnect.R
import com.rohit.bloodconnect.shared_preference.SharedPreferencesHelper
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val jwtToken = SharedPreferencesHelper.getJwtToken(LocalContext.current)

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    // Fetch user data if needed

    ModalNavigationDrawer(
        drawerContent = {
            DrawerContent(navController = navController)
        },
        drawerState = drawerState
    ) {
        Scaffold(

            topBar = {
               HomeScreenTopBar(navController = navController, scope = scope, drawerState = drawerState )
            },
            bottomBar = {
                BottomNavigationBar(
                    navController = navController,
                    items = listOf(BottomNavItem.Home, BottomNavItem.Request, BottomNavItem.Profile)
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate("donate_blood_screen") },
                    shape = CircleShape,
                 containerColor = Color.Red
                ) {
                    Icon(painter = painterResource(id = R.drawable.untitled_design__3_), contentDescription = "Donate Blood")
                }
            },
            floatingActionButtonPosition = FabPosition.Center,

        ) { paddingValues ->
            // Main screen content
            Box(modifier = Modifier.padding(paddingValues)) {
                // You can place your main screen content here
            }
        }
    }
}


