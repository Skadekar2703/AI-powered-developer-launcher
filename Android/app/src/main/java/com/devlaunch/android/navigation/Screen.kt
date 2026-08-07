package com.devlaunch.android.navigation

sealed class Screen(val route: String) {

    data object Login : Screen("login")

    data object Signup : Screen("signup")

    data object ForgotPassword : Screen("forgot_password")

    data object Home : Screen("home")

    object Projects : Screen("projects")

    object AI : Screen("ai")

    object Notifications : Screen("notifications")

    object Profile : Screen("profile")

    object Main : Screen("main")

}