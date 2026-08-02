package com.devlaunch.desktop.feature.auth.services

import com.devlaunch.desktop.feature.auth.model.User
import kotlinx.coroutines.delay

class FakeAuthService : AuthService {
    private var currentUser: User? = null

    override suspend fun login(email: String, password: String): Result<User> {
        delay(1200) // Simulate network delay
        val user = User(email = email, name = email.substringBefore("@").replaceFirstChar { it.uppercase() })
        currentUser = user
        return Result.success(user)
    }

    override suspend fun signup(email: String, password: String): Result<User> {
        delay(1500) // Simulate network delay
        val user = User(email = email, name = email.substringBefore("@").replaceFirstChar { it.uppercase() })
        currentUser = user
        return Result.success(user)
    }

    override suspend fun loginWithGoogle(email: String?, name: String?): Result<User> {
        delay(1000) // Simulate Google Sign-In pop-up delay
        val user = User(
            email = email ?: com.devlaunch.desktop.core.config.Config.mockGoogleEmail,
            name = name ?: com.devlaunch.desktop.core.config.Config.mockGoogleName
        )
        currentUser = user
        return Result.success(user)
    }

    override suspend fun logout(): Result<Unit> {
        delay(500)
        currentUser = null
        return Result.success(Unit)
    }

    override suspend fun checkSession(): Result<User?> {
        delay(1000) // Simulate checking stored session token
        return Result.success(currentUser)
    }

    override suspend fun resetPassword(email: String): Result<Unit> {
        delay(800)
        return Result.success(Unit)
    }
}
