package com.devlaunch.desktop.feature.auth.services

actual fun getAuthService(): AuthService = SupabaseAuthService()
