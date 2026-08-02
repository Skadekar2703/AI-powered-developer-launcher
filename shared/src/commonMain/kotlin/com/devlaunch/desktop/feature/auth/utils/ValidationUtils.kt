package com.devlaunch.desktop.feature.auth.utils

object ValidationUtils {
    private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()

    fun isValidEmail(email: String): Boolean {
        return email.isNotBlank() && email.matches(emailRegex)
    }

    fun isValidPassword(password: String): Boolean {
        return password.isNotBlank() && password.length >= 6
    }
}
