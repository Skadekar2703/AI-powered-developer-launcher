package com.devlaunch.desktop

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.devlaunch.desktop.core.theme.DevLaunchTheme
import com.devlaunch.desktop.feature.auth.navigation.AuthNavigator
import com.devlaunch.desktop.feature.auth.navigation.AuthScreen
import com.devlaunch.desktop.feature.auth.services.getAuthService
import com.devlaunch.desktop.feature.auth.repository.FakeAuthRepository
import com.devlaunch.desktop.feature.auth.viewmodel.AuthViewModel
import com.devlaunch.desktop.feature.auth.ui.screens.SessionCheckingScreen
import com.devlaunch.desktop.feature.auth.ui.screens.WelcomeScreen
import com.devlaunch.desktop.feature.auth.ui.screens.LoginScreen
import com.devlaunch.desktop.feature.auth.ui.screens.SignupScreen
import com.devlaunch.desktop.feature.auth.ui.screens.ForgotPasswordScreen
import com.devlaunch.desktop.feature.auth.ui.screens.AuthenticatedScreen

@Composable
@Preview
fun App() {
    val authService = remember { getAuthService() }
    val authRepository = remember { FakeAuthRepository(authService) }
    val authViewModel = remember { AuthViewModel(authRepository) }
    val navigator = remember { AuthNavigator(AuthScreen.CheckingSession) }

    // Start Check Session on application launch
    LaunchedEffect(Unit) {
        authViewModel.checkSession { user ->
            if (user != null) {
                navigator.navigateTo(AuthScreen.Authenticated, clearStack = true)
            } else {
                navigator.navigateTo(AuthScreen.Welcome, clearStack = true)
            }
        }
    }

    DevLaunchTheme {
        AnimatedContent(
            targetState = navigator.currentScreen,
            transitionSpec = {
                fadeIn(animationSpec = tween(300)) togetherWith fadeOut(animationSpec = tween(300))
            },
            label = "screen_transition"
        ) { screen ->
            when (screen) {
                AuthScreen.CheckingSession -> {
                    SessionCheckingScreen()
                }
                AuthScreen.Welcome -> {
                    WelcomeScreen(
                        onSignInClick = {
                            authViewModel.clearErrors()
                            navigator.navigateTo(AuthScreen.Login)
                        },
                        onCreateAccountClick = {
                            authViewModel.clearErrors()
                            navigator.navigateTo(AuthScreen.Signup)
                        }
                    )
                }
                AuthScreen.Login -> {
                    val state by authViewModel.state.collectAsState()
                    LoginScreen(
                        state = state,
                        onEmailChanged = authViewModel::onEmailChanged,
                        onPasswordChanged = authViewModel::onPasswordChanged,
                        onLoginClick = {
                            authViewModel.login {
                                navigator.navigateTo(AuthScreen.Authenticated, clearStack = true)
                            }
                        },
                        onGoogleClick = {
                            authViewModel.loginWithGoogle {
                                navigator.navigateTo(AuthScreen.Authenticated, clearStack = true)
                            }
                        },
                        onForgotPasswordClick = {
                            authViewModel.clearErrors()
                            navigator.navigateTo(AuthScreen.ForgotPassword)
                        },
                        onCreateAccountClick = {
                            authViewModel.clearErrors()
                            navigator.navigateTo(AuthScreen.Signup)
                        }
                    )
                }
                AuthScreen.Signup -> {
                    val state by authViewModel.state.collectAsState()
                    SignupScreen(
                        state = state,
                        onEmailChanged = authViewModel::onSignupEmailChanged,
                        onPasswordChanged = authViewModel::onSignupPasswordChanged,
                        onConfirmPasswordChanged = authViewModel::onConfirmPasswordChanged,
                        onSignupClick = {
                            authViewModel.signup {
                                navigator.navigateTo(AuthScreen.Authenticated, clearStack = true)
                            }
                        },
                        onGoogleClick = {
                            authViewModel.loginWithGoogle {
                                navigator.navigateTo(AuthScreen.Authenticated, clearStack = true)
                            }
                        },
                        onLoginClick = {
                            authViewModel.clearErrors()
                            navigator.navigateTo(AuthScreen.Login)
                        }
                    )
                }
                AuthScreen.ForgotPassword -> {
                    val state by authViewModel.state.collectAsState()
                    ForgotPasswordScreen(
                        state = state,
                        onEmailChanged = authViewModel::onForgotEmailChanged,
                        onResetClick = authViewModel::resetPassword,
                        onBackToLoginClick = {
                            authViewModel.clearErrors()
                            navigator.navigateTo(AuthScreen.Login)
                        }
                    )
                }
                AuthScreen.Authenticated -> {
                    val state by authViewModel.state.collectAsState()
                    AuthenticatedScreen(
                        state = state,
                        onLogoutClick = {
                            authViewModel.logout {
                                navigator.navigateTo(AuthScreen.Welcome, clearStack = true)
                            }
                        }
                    )
                }
            }
        }
    }
}