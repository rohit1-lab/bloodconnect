package com.rohit.bloodconnect.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
class PermissionHandler(
    private val context: Context,
    private val requestPermissionLauncher: ActivityResultLauncher<String>,
    private val onPermissionGranted: () -> Unit,
    private val onPermissionDenied: () -> Unit,
    private val onPermissionDeniedPermanently: () -> Unit
) {

    fun checkAndRequestLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
                onPermissionGranted()
            }
            else -> {
                requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    fun isPermissionPermanentlyDenied(): Boolean {
        return !ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, android.Manifest.permission.ACCESS_FINE_LOCATION)
    }
}