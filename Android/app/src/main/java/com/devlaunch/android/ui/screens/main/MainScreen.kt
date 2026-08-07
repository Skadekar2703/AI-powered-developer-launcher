package com.devlaunch.android.ui.screens.main

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devlaunch.android.navigation.BottomNavItem
import com.devlaunch.android.ui.screens.ai.AiScreen
import com.devlaunch.android.ui.screens.home.HomeScreen
import com.devlaunch.android.ui.screens.home.components.HomeBottomBar
import com.devlaunch.android.ui.screens.notification.NotificationScreen
import com.devlaunch.android.ui.screens.profile.ProfileScreen
import com.devlaunch.android.ui.screens.projects.ProjectsScreen

@Composable
fun MainScreen(
    navController: NavHostController
) {

    val bottomNavController = rememberNavController()

    var selectedRoute by remember {
        mutableStateOf(BottomNavItem.Home.route)
    }

    Scaffold(

        bottomBar = {

            HomeBottomBar(

                selectedRoute = selectedRoute,

                onItemClick = { item ->

                    selectedRoute = item.route

                    bottomNavController.navigate(item.route) {

                        launchSingleTop = true

                        restoreState = true

                        popUpTo(bottomNavController.graph.startDestinationId) {
                            saveState = true
                        }

                    }

                }

            )

        }

    ) { padding ->

        NavHost(

            navController = bottomNavController,

            startDestination = BottomNavItem.Home.route

        ) {

            composable(BottomNavItem.Home.route) {

                HomeScreen()

            }

            composable(BottomNavItem.Projects.route) {

                ProjectsScreen()

            }

            composable(BottomNavItem.AI.route) {

                AiScreen()

            }

            composable(BottomNavItem.Notifications.route) {

                NotificationScreen()

            }

            composable(BottomNavItem.Profile.route) {

                ProfileScreen()

            }

        }

    }

}