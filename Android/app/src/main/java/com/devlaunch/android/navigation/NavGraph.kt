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
import com.devlaunch.android.ui.screens.auth.viewmodel.AuthViewModel



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

                    onLoginClick = viewModel::login,

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

    }

}