package com.devlaunch.android.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.automirrored.outlined.Chat

sealed class BottomNavItem(

    val route: String,
    val title: String,
    val icon: ImageVector

) {

    object Home : BottomNavItem(
        route = "home",
        title = "Home",
        icon = Icons.Outlined.Home
    )

    object Projects : BottomNavItem(
        route = "projects",
        title = "Projects",
        icon = Icons.Outlined.Folder
    )

    object AI : BottomNavItem(
        route = "ai",
        title = "AI",
        Icons.AutoMirrored.Outlined.Chat
    )

    object Notifications : BottomNavItem(
        route = "notifications",
        title = "Alerts",
        icon = Icons.Outlined.Notifications
    )

    object Profile : BottomNavItem(
        route = "profile",
        title = "Profile",
        icon = Icons.Outlined.AccountCircle
    )

}