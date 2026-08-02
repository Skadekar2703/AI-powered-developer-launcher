package com.devlaunch.desktop.feature.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devlaunch.desktop.feature.auth.model.AuthState
import com.devlaunch.desktop.feature.auth.model.User
import com.devlaunch.desktop.feature.auth.repository.AuthRepository
import com.devlaunch.desktop.feature.auth.utils.ValidationUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    fun checkSession(onDone: (User?) -> Unit) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val result = authRepository.checkSession()
            val user = result.getOrNull()
            _state.update {
                it.copy(
                    isLoading = false,
                    user = user
                )
            }
            onDone(user)
        }
    }

    // --- Login Form Actions ---

    fun onEmailChanged(email: String) {
        _state.update {
            it.copy(
                email = email,
                emailError = if (email.isNotBlank()) null else it.emailError
            )
        }
    }

    fun onPasswordChanged(password: String) {
        _state.update {
            it.copy(
                password = password,
                passwordError = if (password.isNotBlank()) null else it.passwordError
            )
        }
    }

    fun login(onSuccess: () -> Unit) {
        val email = _state.value.email.trim()
        val password = _state.value.password

        val emailError = when {
            email.isBlank() -> "Email address is required"
            !ValidationUtils.isValidEmail(email) -> "Please enter a valid email address"
            else -> null
        }

        val passwordError = when {
            password.isBlank() -> "Password is required"
            !ValidationUtils.isValidPassword(password) -> "Password must be at least 6 characters"
            else -> null
        }

        if (emailError != null || passwordError != null) {
            _state.update {
                it.copy(
                    emailError = emailError,
                    passwordError = passwordError
                )
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            val result = authRepository.login(email, password)
            if (result.isSuccess) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        user = result.getOrNull(),
                        email = "",
                        password = ""
                    )
                }
                onSuccess()
            } else {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = result.exceptionOrNull()?.message ?: "Login failed"
                    )
                }
            }
        }
    }

    // --- Signup Form Actions ---

    fun onSignupEmailChanged(email: String) {
        _state.update {
            it.copy(
                signupEmail = email,
                signupEmailError = if (email.isNotBlank()) null else it.signupEmailError
            )
        }
    }

    fun onSignupPasswordChanged(password: String) {
        _state.update {
            it.copy(
                signupPassword = password,
                signupPasswordError = if (password.isNotBlank()) null else it.signupPasswordError
            )
        }
    }

    fun onConfirmPasswordChanged(password: String) {
        _state.update {
            it.copy(
                confirmPassword = password,
                confirmPasswordError = if (password.isNotBlank()) null else it.confirmPasswordError
            )
        }
    }

    fun signup(onSuccess: () -> Unit) {
        val email = _state.value.signupEmail.trim()
        val password = _state.value.signupPassword
        val confirmPassword = _state.value.confirmPassword

        val emailError = when {
            email.isBlank() -> "Email address is required"
            !ValidationUtils.isValidEmail(email) -> "Please enter a valid email address"
            else -> null
        }

        val passwordError = when {
            password.isBlank() -> "Password is required"
            !ValidationUtils.isValidPassword(password) -> "Password must be at least 6 characters"
            else -> null
        }

        val confirmError = when {
            confirmPassword.isBlank() -> "Please confirm your password"
            password != confirmPassword -> "Passwords do not match"
            else -> null
        }

        if (emailError != null || passwordError != null || confirmError != null) {
            _state.update {
                it.copy(
                    signupEmailError = emailError,
                    signupPasswordError = passwordError,
                    confirmPasswordError = confirmError
                )
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            val result = authRepository.signup(email, password)
            if (result.isSuccess) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        user = result.getOrNull(),
                        signupEmail = "",
                        signupPassword = "",
                        confirmPassword = ""
                    )
                }
                onSuccess()
            } else {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = result.exceptionOrNull()?.message ?: "Registration failed"
                    )
                }
            }
        }
    }

    // --- Google Login Action ---

    fun loginWithGoogle(email: String? = null, name: String? = null, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            val result = authRepository.loginWithGoogle(email, name)
            if (result.isSuccess) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        user = result.getOrNull()
                    )
                }
                onSuccess()
            } else {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = result.exceptionOrNull()?.message ?: "Google login failed"
                    )
                }
            }
        }
    }

    // --- Forgot Password Actions ---

    fun onForgotEmailChanged(email: String) {
        _state.update {
            it.copy(
                forgotEmail = email,
                forgotEmailError = if (email.isNotBlank()) null else it.forgotEmailError
            )
        }
    }

    fun resetPassword() {
        val email = _state.value.forgotEmail.trim()
        val emailError = when {
            email.isBlank() -> "Email address is required"
            !ValidationUtils.isValidEmail(email) -> "Please enter a valid email address"
            else -> null
        }

        if (emailError != null) {
            _state.update {
                it.copy(
                    forgotEmailError = emailError,
                    forgotSuccessMessage = null
                )
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null, forgotSuccessMessage = null) }
            val result = authRepository.resetPassword(email)
            if (result.isSuccess) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        forgotSuccessMessage = "Reset link has been sent to your email.",
                        forgotEmail = ""
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = result.exceptionOrNull()?.message ?: "Failed to send reset link"
                    )
                }
            }
        }
    }

    // --- Logout Action ---

    fun logout(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val result = authRepository.logout()
            if (result.isSuccess) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        user = null
                    )
                }
                onSuccess()
            } else {
                _state.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }

    fun clearErrors() {
        _state.update {
            it.copy(
                error = null,
                emailError = null,
                passwordError = null,
                signupEmailError = null,
                signupPasswordError = null,
                confirmPasswordError = null,
                forgotEmailError = null,
                forgotSuccessMessage = null
            )
        }
    }
}
