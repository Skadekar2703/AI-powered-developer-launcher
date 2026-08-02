package com.devlaunch.desktop.feature.auth.services

import com.devlaunch.desktop.feature.auth.model.User

interface AuthService {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun signup(email: String, password: String): Result<User>
    suspend fun loginWithGoogle(email: String? = null, name: String? = null): Result<User>
    suspend fun logout(): Result<Unit>
    suspend fun checkSession(): Result<User?>
    suspend fun resetPassword(email: String): Result<Unit>
}
