package com.devlaunch.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// =====================================================
// LIGHT COLOR SCHEME
// =====================================================
private val LightColors = lightColorScheme(
    primary = DevLightPrimary,
    onPrimary = Color.White,
    primaryContainer = DevPrimaryContainer,
    onPrimaryContainer = Color.White,

    secondary = DevSecondary,
    onSecondary = Color.White,
    secondaryContainer = DevSecondaryContainer,
    onSecondaryContainer = Color.White,

    tertiary = DevTertiary,
    onTertiary = DevLightTextPrimary,

    background = DevLightBackground,
    onBackground = DevLightTextPrimary,       // clear dark text on light bg

    surface = DevLightSurface,
    onSurface = DevLightTextPrimary,          // clear dark text on light surface
    surfaceVariant = DevLightSurfaceVariant,
    onSurfaceVariant = DevLightTextSecondary,

    outline = DevLightOutline,
    error = DevError,
    onError = DevOnError
)

// =====================================================
// DARK COLOR SCHEME
// =====================================================
private val DarkColors = darkColorScheme(
    primary = DevDarkPrimary,
    onPrimary = Color.White,
    primaryContainer = DevPrimaryContainer,
    onPrimaryContainer = Color.White,

    secondary = DevSecondary,
    onSecondary = DevDarkTextPrimary,
    secondaryContainer = DevSecondaryContainer,
    onSecondaryContainer = Color.White,

    tertiary = DevTertiary,
    onTertiary = DevDarkBackground,

    background = DevDarkBackground,
    onBackground = DevDarkTextPrimary,        // clear white text on dark bg

    surface = DevDarkSurface,
    onSurface = DevDarkTextPrimary,           // clear white text on dark surface
    surfaceVariant = DevDarkSurfaceVariant,
    onSurfaceVariant = DevDarkTextSecondary,

    outline = DevDarkOutline,
    error = DevError,
    onError = DevOnError
)

/**
 * Single app-wide theme. Automatically follows the system light/dark
 * setting, or pass `darkTheme` explicitly to force one mode.
 *
 * Usage:
 *   DevLaunchTheme {
 *       // your screen content
 *   }
 */
@Composable
fun DevLaunchTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

/**
 * Legacy alias — MainActivity.kt (and possibly other entry points)
 * call this name directly. Kept as a thin wrapper around
 * [DevLaunchTheme] so nothing else needs to change.
 */
@Composable
fun DevLaunchAndroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) = DevLaunchTheme(darkTheme = darkTheme, content = content)