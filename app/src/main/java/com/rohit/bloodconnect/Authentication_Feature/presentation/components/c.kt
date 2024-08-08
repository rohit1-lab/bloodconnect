package com.rohit.bloodconnect.Authentication_Feature.presentation.components

import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.rohit.bloodconnect.Authentication_Feature.navigation.Screen
import com.rohit.bloodconnect.Manifest
import com.rohit.bloodconnect.R

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DonateBloodFAB(navController: NavHostController) {

    val permissionsManager = rememberPermissionHandler(
        onPermissionGranted = {
            // Navigate to the DonateBloodScreen if permission is granted
            navController.navigate(Screen.DonateBlood.route)
        },
        onPermissionDenied = {
            // Optionally show a message explaining why the permission is needed
        },
        onPermissionDeniedPermanently = {
            // Optionally show a message or navigate to settings to enable the user to grant permission
            // For example, you can show a dialpeog with a button to open app settings
        }
    )

    FloatingActionButton(
        onClick = {
            permissionsManager.checkAndRequestLocationPermission()
        },
        shape = CircleShape,

        containerColor = Color.Red,
        modifier = Modifier.size(30.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.untitled_design__3_),
            contentDescription = "Donate Blood"
        )
    }
}


