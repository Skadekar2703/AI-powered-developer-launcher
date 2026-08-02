package com.devlaunch.desktop.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = DevPrimary,
    onPrimary = DevOnPrimary,
    secondary = DevSecondary,
    onSecondary = DevOnSecondary,
    background = DevBackground,
    onBackground = DevTextPrimary,
    surface = DevSurface,
    onSurface = DevTextPrimary,
    error = DevError,
    onError = DevOnError
)

@Composable
fun DevLaunchTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = DevTypography,
        content = content
    )
}
