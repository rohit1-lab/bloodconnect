package com.rohit.bloodconnect.Authentication_Feature.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(scaffoldState: DrawerState) {
    TopAppBar(
        title = { Text("BloodConnect") },
        navigationIcon = {
            IconButton(onClick = { scaffoldState.currentValue }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = { /* Handle notification click */ }) {
                Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
            }
        }
    )
}