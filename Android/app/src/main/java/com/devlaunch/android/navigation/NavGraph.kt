package com.devlaunch.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devlaunch.android.ui.screens.auth.screen.forgotpassword.ForgotPasswordScreen
import com.devlaunch.android.ui.screens.auth.screen.login.LoginScreen
import com.devlaunch.android.ui.screens.auth.screen.signup.SignupScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.devlaunch.android.ui.screens.home.HomeScreen
import com.devlaunch.android.ui.screens.auth.viewmodel.AuthViewModel
import com.devlaunch.android.ui.screens.projects.ProjectsScreen
import com.devlaunch.android.ui.screens.profile.ProfileScreen
import com.devlaunch.android.ui.screens.ai.AiScreen
import com.devlaunch.android.ui.screens.main.MainScreen
import com.devlaunch.android.ui.screens.notification.NotificationScreen



@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    val viewModel: AuthViewModel = viewModel()

    val state by viewModel.state.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {


        composable(Screen.Login.route) {

            LoginScreen(


                state = state,

                onEmailChanged = viewModel::onEmailChanged,

                onPasswordChanged = viewModel::onPasswordChanged,

                onLoginClick = {
                    viewModel.login {
                        navController.navigate(Screen.Main.route) {
                            popUpTo(Screen.Login.route) {
                                inclusive = true
                            }
                        }
                    }
                },

                onGoogleClick = {},

                onForgotPasswordClick = {
                    navController.navigate(Screen.ForgotPassword.route)
                },

                onCreateAccountClick = {
                    navController.navigate(Screen.Signup.route)
                }

            )

        }



        composable(Screen.Signup.route) {

            SignupScreen(

                state = state,

                onFullNameChanged = viewModel::onFullNameChanged,

                onEmailChanged = viewModel::onSignupEmailChanged,

                onPasswordChanged = viewModel::onSignupPasswordChanged,

                onConfirmPasswordChanged = viewModel::onConfirmPasswordChanged,

                onSignupClick = viewModel::signup,

                onGoogleClick = {},

                onLoginClick = {
                    navController.popBackStack()
                }
            )

        }

        composable(Screen.ForgotPassword.route) {

            ForgotPasswordScreen(

                state = state,

                onEmailChanged = viewModel::onForgotEmailChanged,

                onResetClick = viewModel::resetPassword,

                onBackToLoginClick = {
                    navController.popBackStack()
                }

            )

        }

        composable(Screen.Main.route) {
            MainScreen(navController)
        }

        composable(Screen.Home.route) {
            HomeScreen()
        }

        composable(Screen.Projects.route) {
            ProjectsScreen()
        }

        composable(Screen.AI.route) {
            AiScreen()
        }

        composable(Screen.Notifications.route) {
            NotificationScreen()
        }

        composable(Screen.Profile.route) {
            ProfileScreen()
        }

    }

}