package com.devlaunch.android.ui.screens.auth.viewmodel

import androidx.lifecycle.ViewModel
import com.devlaunch.android.ui.screens.auth.model.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

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

    // ---------------- Login ----------------

    fun login() {

        if (_state.value.email.isBlank()) {

            _state.value = _state.value.copy(
                emailError = "Email is required"
            )
            return
        }

        if (_state.value.password.isBlank()) {

            _state.value = _state.value.copy(
                passwordError = "Password is required"
            )
            return
        }

        // Firebase Login नंतर इथे येईल
    }

    // ---------------- Signup ----------------

    fun signup() {

        if (_state.value.signupEmail.isBlank()) {

            _state.value = _state.value.copy(
                signupEmailError = "Email is required"
            )
            return
        }

        if (_state.value.signupPassword.isBlank()) {

            _state.value = _state.value.copy(
                signupPasswordError = "Password is required"
            )
            return
        }

        if (_state.value.confirmPassword.isBlank()) {

            _state.value = _state.value.copy(
                confirmPasswordError = "Confirm Password is required"
            )
            return
        }

        if (_state.value.signupPassword != _state.value.confirmPassword) {

            _state.value = _state.value.copy(
                confirmPasswordError = "Passwords do not match"
            )
            return
        }



        // Firebase Signup नंतर इथे येईल
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

        if (_state.value.forgotEmail.isBlank()) {

            _state.value = _state.value.copy(
                forgotEmailError = "Email is required"
            )

            return
        }

        _state.value = _state.value.copy(
            forgotSuccessMessage = "Password reset link has been sent to ${_state.value.forgotEmail}"
        )

    }

}