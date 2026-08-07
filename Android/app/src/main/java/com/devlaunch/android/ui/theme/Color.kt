package com.devlaunch.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// =====================================================
// BRAND / ACCENT COLORS (shared across light & dark)
// =====================================================
val DevPrimaryContainer = Color(0xFFE24EF3) // orchid pink
val DevSecondary = Color(0xFFFDAAFF)
val DevSecondaryContainer = Color(0xFF702678)
val DevTertiary = Color(0xFFDCC754)         // warm gold accent
val DevError = Color(0xFFFFB4AB)
val DevOnError = Color(0xFF690005)

// =====================================================
// LIGHT THEME
// =====================================================
val DevLightBackground = Color(0xFFF1F5F9)
val DevLightSurface = Color.White
val DevLightSurfaceVariant = Color(0xFFE7E0EC)
val DevLightPrimary = Color(0xFF7C3AED)
val DevLightOutline = Color(0xFFD1D5DB)     // subtle 10% black border

val DevLightTextPrimary = Color(0xFF111827)   // near-black, high contrast on light bg
val DevLightTextSecondary = Color(0xFF475569)
val DevLightTextMuted = Color(0xFF94A3B8)

// =====================================================
// DARK THEME
// =====================================================
val DevDarkBackground = Color(0xFF0F172A)
val DevDarkSurface = Color(0xFF1E293B)
val DevDarkSurfaceVariant = Color(0xFF3D313C)
val DevDarkPrimary = Color(0xFFA855F7)
val DevDarkOutline = Color(0x26FFFFFF)      // subtle 15% white border

val DevDarkTextPrimary = Color.White          // pure white, high contrast on dark bg
val DevDarkTextSecondary = Color(0xFFA0A0A0)
val DevDarkTextMuted = Color(0xFF64748B)

// =====================================================
// GLASS / OVERLAY (used on dark, premium UI look)
// =====================================================
val DevGlassFill = Color(0x66201F1F)        // ~40% translucent panel fill
val DevNeutralBtn = Color(0xFF1A1A1A)

// =====================================================
// LEGACY ALIASES — now theme-aware
// -----------------------------------------------------
// Your screens/components (DevButton, DevCard, DevTextField,
// LoginScreen, SignupScreen, HomeScreen, etc.) reference these
// names DIRECTLY as top-level colors instead of going through
// MaterialTheme.colorScheme.
//
// IMPORTANT: these are declared as Composable *properties*
// (`val X: Color @Composable get() = ...`), not plain constants.
// The call sites don't change at all (still just `DevTextPrimary`,
// no parentheses) — Kotlin treats the property getter as a normal
// function call under the hood, so every existing screen file
// keeps compiling unchanged, but now resolves to the CURRENT
// theme (light or dark) instead of being frozen on dark colors.
//
// This is what was broken before: DevTextPrimary was hardcoded to
// white, so in light mode you got white text on a white/light
// background (invisible). Now it pulls from whichever ColorScheme
// is actually active.
// =====================================================

/** True when the app is currently rendering in dark mode. */
val isAppInDarkTheme: Boolean
    @Composable get() = MaterialTheme.colorScheme.background == DevDarkBackground

val DevBackground: Color
    @Composable get() = MaterialTheme.colorScheme.background

val DevSurface: Color
    @Composable get() = MaterialTheme.colorScheme.surface

val DevBorder: Color
    @Composable get() = MaterialTheme.colorScheme.outline

val DevPrimary: Color
    @Composable get() = MaterialTheme.colorScheme.primary

val DevPrimaryPressed: Color
    @Composable get() = if (isAppInDarkTheme) Color(0xFF7C3AED) else Color(0xFF6D28D9)

val DevOnPrimary: Color
    @Composable get() = MaterialTheme.colorScheme.onPrimary

val DevTextPrimary: Color
    @Composable get() = MaterialTheme.colorScheme.onBackground

val DevTextSecondary: Color
    @Composable get() = MaterialTheme.colorScheme.onSurfaceVariant

val DevTextMuted: Color
    @Composable get() = if (isAppInDarkTheme) DevDarkTextMuted else DevLightTextMuted

val DevTextFaint: Color
    @Composable get() = if (isAppInDarkTheme) Color(0xFF9F8A9D) else Color(0xFFB0A0AD)