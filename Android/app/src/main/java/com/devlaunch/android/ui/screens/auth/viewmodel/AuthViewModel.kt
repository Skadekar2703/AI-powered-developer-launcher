package com.devlaunch.android.ui.screens.auth.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.devlaunch.android.ui.screens.auth.model.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import com.devlaunch.android.core.network.SupabaseClientProvider
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class AuthViewModel : ViewModel() {

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS
            .matcher(email)
            .matches()
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordRegex =
            Regex("^(?=.*[A-Za-z])(?=.*\\d).{8,}$")

        return passwordRegex.matches(password)
    }

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    fun onFullNameChanged(name: String) {
        _state.value = _state.value.copy(
            fullName = name,
            fullNameError = null
        )
    }

    // ---------------- Login ----------------

    fun onEmailChanged(email: String) {
        _state.value = _state.value.copy(
            email = email,
            emailError = null
        )
    }

    fun onPasswordChanged(password: String) {
        _state.value = _state.value.copy(
            password = password,
            passwordError = null
        )
    }

    fun login(onSuccess: () -> Unit) {

        _state.value = _state.value.copy(
            emailError = null,
            passwordError = null,
            error = null
        )

        val email = _state.value.email.trim()
        val password = _state.value.password.trim()

        if (email.isBlank()) {
            _state.value = _state.value.copy(emailError = "Email is required")
            return
        }
        if (!isValidEmail(email)) {
            _state.value = _state.value.copy(emailError = "Enter a valid email address")
            return
        }

        if (password.isBlank()) {
            _state.value = _state.value.copy(passwordError = "Password is required")
            return
        }
        if (!isValidPassword(password)) {
            _state.value = _state.value.copy(
                passwordError = "Password must contain at least 8 characters, letters and numbers"
            )
            return
        }

        viewModelScope.launch {

            _state.value = _state.value.copy(isLoading = true, error = null)

            try {

                SupabaseClientProvider.client.auth.signInWith(Email) {
                    this.email = email
                    this.password = password
                }

                _state.value = _state.value.copy(isLoading = false)

                onSuccess()

            } catch (e: Exception) {

                Log.e("Login", e.stackTraceToString())

                val message = when {
                    e.message?.contains("invalid_credentials", true) == true ->
                        "Invalid email or password."

                    e.message?.contains("network", true) == true ||
                            e.message?.contains("unable to resolve host", true) == true ->
                        "Please check your internet connection."

                    else -> "Something went wrong. Please try again."
                }

                _state.value = _state.value.copy(
                    isLoading = false,
                    error = message
                )
            }
        }
    }

    // ---------------- Signup ----------------

    fun onSignupEmailChanged(email: String) {
        _state.value = _state.value.copy(
            signupEmail = email,
            signupEmailError = null
        )
    }

    fun onSignupPasswordChanged(password: String) {
        _state.value = _state.value.copy(
            signupPassword = password,
            signupPasswordError = null
        )
    }

    fun onConfirmPasswordChanged(password: String) {
        _state.value = _state.value.copy(
            confirmPassword = password,
            confirmPasswordError = null
        )
    }

    fun signup() {

        val fullName = _state.value.fullName.trim()
        val email = _state.value.signupEmail.trim()
        val password = _state.value.signupPassword.trim()
        val confirmPassword = _state.value.confirmPassword.trim()

        if (fullName.isBlank()) {
            _state.value = _state.value.copy(fullNameError = "Full name is required")
            return
        }

        if (email.isBlank()) {
            _state.value = _state.value.copy(signupEmailError = "Email is required")
            return
        }
        if (!isValidEmail(email)) {
            _state.value = _state.value.copy(signupEmailError = "Enter a valid email address")
            return
        }

        if (password.isBlank()) {
            _state.value = _state.value.copy(signupPasswordError = "Password is required")
            return
        }

        if (confirmPassword.isBlank()) {
            _state.value = _state.value.copy(confirmPasswordError = "Confirm Password is required")
            return
        }

        if (!isValidPassword(password)) {
            _state.value = _state.value.copy(
                signupPasswordError = "Password must contain at least 8 characters, letters and numbers"
            )
            return
        }

        if (password != confirmPassword) {
            _state.value = _state.value.copy(confirmPasswordError = "Passwords do not match")
            return
        }

        viewModelScope.launch {

            _state.value = _state.value.copy(isLoading = true, error = null)

            try {

                SupabaseClientProvider.client.auth.signUpWith(Email) {
                    this.email = email
                    this.password = password

                    data = buildJsonObject {
                        put("full_name", fullName)
                    }
                }

                _state.value = _state.value.copy(
                    isLoading = false,
                    successMessage = "Account created successfully."
                )

            } catch (e: Exception) {

                Log.e("Signup", e.stackTraceToString())

                val message = when {
                    e.message?.contains("already registered", true) == true ->
                        "This email is already registered."

                    e.message?.contains("email", true) == true ->
                        "Please enter a valid email."

                    else -> "Unable to create account. Please try again."
                }

                _state.value = _state.value.copy(
                    isLoading = false,
                    error = message
                )
            }
        }
    }

    // ---------------- Forgot Password ----------------

    fun onForgotEmailChanged(email: String) {
        _state.value = _state.value.copy(
            forgotEmail = email,
            forgotEmailError = null,
            forgotSuccessMessage = null
        )
    }

    fun resetPassword() {

        val email = _state.value.forgotEmail.trim()

        if (email.isBlank()) {
            _state.value = _state.value.copy(forgotEmailError = "Email is required")
            return
        }

        if (!isValidEmail(email)) {
            _state.value = _state.value.copy(forgotEmailError = "Enter a valid email address")
            return
        }

        viewModelScope.launch {

            _state.value = _state.value.copy(
                isLoading = true,
                forgotEmailError = null,
                forgotSuccessMessage = null
            )

            try {

                SupabaseClientProvider.client.auth.resetPasswordForEmail(email)

                _state.value = _state.value.copy(
                    isLoading = false,
                    forgotSuccessMessage = "Password reset link has been sent to $email"
                )

            } catch (e: Exception) {

                Log.e("ResetPassword", e.stackTraceToString())

                val message = when {
                    e.message?.contains("network", true) == true ||
                            e.message?.contains("unable to resolve host", true) == true ->
                        "Please check your internet connection."

                    else -> "Unable to send reset link. Please try again."
                }

                _state.value = _state.value.copy(
                    isLoading = false,
                    forgotEmailError = message
                )
            }
        }
    }
}