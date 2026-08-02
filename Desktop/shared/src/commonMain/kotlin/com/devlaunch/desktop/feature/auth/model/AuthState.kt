package com.devlaunch.desktop.feature.auth.model

data class AuthState(
    // Common
    val isLoading: Boolean = false,
    val error: String? = null,
    val user: User? = null,

    // Login Form
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,

    // Signup Form
    val signupEmail: String = "",
    val signupPassword: String = "",
    val confirmPassword: String = "",
    val signupEmailError: String? = null,
    val signupPasswordError: String? = null,
    val confirmPasswordError: String? = null,

    // Forgot Password Form
    val forgotEmail: String = "",
    val forgotEmailError: String? = null,
    val forgotSuccessMessage: String? = null
)
