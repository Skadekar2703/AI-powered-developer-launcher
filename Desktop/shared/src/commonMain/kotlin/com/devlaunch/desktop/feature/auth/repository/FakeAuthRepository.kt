package com.devlaunch.desktop.feature.auth.repository

import com.devlaunch.desktop.feature.auth.model.User
import com.devlaunch.desktop.feature.auth.services.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeAuthRepository(private val authService: AuthService) : AuthRepository {
    private val _currentUser = MutableStateFlow<User?>(null)
    override val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    override suspend fun login(email: String, password: String): Result<User> {
        return authService.login(email, password).onSuccess { user ->
            _currentUser.value = user
        }
    }

    override suspend fun signup(email: String, password: String): Result<User> {
        return authService.signup(email, password).onSuccess { user ->
            _currentUser.value = user
        }
    }

    override suspend fun loginWithGoogle(email: String?, name: String?): Result<User> {
        return authService.loginWithGoogle(email, name).onSuccess { user ->
            _currentUser.value = user
        }
    }

    override suspend fun logout(): Result<Unit> {
        return authService.logout().onSuccess {
            _currentUser.value = null
        }
    }

    override suspend fun checkSession(): Result<User?> {
        return authService.checkSession().onSuccess { user ->
            _currentUser.value = user
        }
    }

    override suspend fun resetPassword(email: String): Result<Unit> {
        return authService.resetPassword(email)
    }
}
