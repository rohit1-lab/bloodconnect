package com.example.bloodconnect.ui.auth

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.rohit.bloodconnect.Authentication_Feature.navigation.Screen
import com.rohit.bloodconnect.Authentication_Feature.presentation.events.AuthEvent
import com.rohit.bloodconnect.Authentication_Feature.presentation.states.AuthState
import com.rohit.bloodconnect.Authentication_Feature.presentation.viewmodel.AuthViewModel
import com.rohit.bloodconnect.R
import com.rohit.bloodconnect.shared_preference.SharedPreferencesHelper

@Composable
fun LoginScreen(navController: NavHostController) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val authState by authViewModel.authState.observeAsState()

    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val googleSignInClient = remember {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        GoogleSignIn.getClient(context, gso)
    }
    val activityResultLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(task, authViewModel)
        }
    }

    when (authState) {
        is AuthState.Loading -> CircularProgressIndicator()
        is AuthState.Success -> {
            val token = (authState as AuthState.Success).data.jwt
            LaunchedEffect(authState) {

                SharedPreferencesHelper.saveJwtToken(context, token)

                onSignInSuccess(navController)
            }

        }
        is AuthState.Error -> {
            val error = (authState as AuthState.Error).error
            if (error != null) {
                Text(text = error, color = MaterialTheme.colorScheme.error)
            }
        }
        else -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { authViewModel.onEvent(AuthEvent.Login(email, password)) }) {
                    Text("Login")
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { activityResultLauncher.launch(googleSignInClient.signInIntent) }) {
                    Text("Login with Google")
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = { navController.navigate(Screen.SignUp.route) }) {
                    Text("Don't have an account? Sign Up")
                }
            }
        }
    }


}
private fun onSignInSuccess(navController: NavHostController){

    navController.navigate(Screen.Home.route)
}
private fun handleSignInResult(task: Task<GoogleSignInAccount>, viewModel: AuthViewModel) {
    try {
        val account = task.getResult(ApiException::class.java)
        val idToken = account?.idToken
        if (idToken != null) {
            viewModel.onEvent(AuthEvent.GoogleSignIn(idToken))

        }
        else {
            Log.e("LoginScreen", "Google Sign-In failed: ID token is null")
        }
    } catch (e: ApiException) {

    }
}