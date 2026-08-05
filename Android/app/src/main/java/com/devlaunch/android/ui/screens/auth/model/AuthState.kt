package com.devlaunch.android.ui.screens.auth.model

data class AuthState(

    // Login
    val email: String = "",
    val password: String = "",

    // Signup
    val signupEmail: String = "",
    val signupPassword: String = "",
    val confirmPassword: String = "",

    // Validation
    val emailError: String? = null,
    val passwordError: String? = null,

    val signupEmailError: String? = null,
    val signupPasswordError: String? = null,
    val confirmPasswordError: String? = null,

    // Common
    val isLoading: Boolean = false,
    val error: String? = null,

    // Forgot Password
    val forgotEmail: String = "",
    val forgotEmailError: String? = null,
    val forgotSuccessMessage: String? = null,

    val fullName: String = "",
    val fullNameError: String? = null,

)