package com.rohit.bloodconnect.Authentication_Feature.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun DrawerContent(navController: NavHostController) {
    Column {
        Text(text = "Drawer Item 1", modifier = Modifier.padding(16.dp))
        Text(text = "Drawer Item 2", modifier = Modifier.padding(16.dp))
        // Add more items as needed
    }
}