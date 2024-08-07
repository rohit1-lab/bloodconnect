package com.rohit.bloodconnect.Authentication_Feature.navigation

sealed class Screen(val route: String) {
     object SignUp: Screen("signup")
      object Login: Screen("login")
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Onboarding : Screen("onboarding")

    object DonateBlood : Screen("donate")
    object Request : Screen("request")
    object MyProfile : Screen("myprofile")
}
