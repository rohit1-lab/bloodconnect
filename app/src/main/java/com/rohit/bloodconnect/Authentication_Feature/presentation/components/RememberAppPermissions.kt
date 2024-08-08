package com.rohit.bloodconnect.Authentication_Feature.presentation.components

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.rohit.bloodconnect.Authentication_Feature.util.PermissionHandler


@Composable
fun rememberPermissionHandler(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit,
    onPermissionDeniedPermanently: () -> Unit
): PermissionHandler {
    val context = LocalContext.current
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            onPermissionGranted()
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                onPermissionDenied()
            } else {
                onPermissionDeniedPermanently()
            }
        }
    }

    return remember(context) {
        PermissionHandler(
            context,
            requestPermissionLauncher,
            onPermissionGranted,
            onPermissionDenied,
            onPermissionDeniedPermanently
        )
    }
}