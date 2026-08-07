package com.devlaunch.android.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// ---- Premium dark palette (matches the glass/gradient mockup) ----

val DevSurfaceVariant = Color(0xFF3D313C)
val DevSurfaceHigh = Color(0xFF312731)
val DevOutlineVariant = Color(0x26FFFFFF) // subtle 15% white border
val DevGlassFill = Color(0x66201F1F)      // ~40% translucent panel fill

       // orchid pink
val DevPrimaryContainer = Color(0xFFE24EF3)
val DevSecondary = Color(0xFFFDAAFF)
val DevSecondaryContainer = Color(0xFF702678)
val DevTertiary = Color(0xFFDCC754)       // warm gold accent
val DevError = Color(0xFFFFB4AB)
//val DevBackground = Color(0xFF1A1019)
//val DevPrimary = Color(0xFFFDAAFF)
//val DevSurface = Color(0xFF221822)
//val DevTextPrimary = Color(0xFFF0DDEB)
//val DevTextMuted = Color(0xFFD7C0D3)
val DevTextFaint = Color(0xFF9F8A9D)

// Gradient used for the greeting headline text ("Good morning, Alex")
val DevGreetingGradient = Brush.linearGradient(
    colors = listOf(Color(0xFFAFC6FF), Color(0xFFC9BFFF))
)

// Soft glow gradient behind the AI suggestion card
val DevGlowGradient = Brush.radialGradient(
    colors = listOf(DevSecondary.copy(alpha = 0.18f), Color.Transparent)
)

/**
 * Frosted "glass panel" look shared by every card in the premium UI:
 * translucent dark fill + a faint 1dp border, so content behind (gradients,
 * background blobs) subtly shows through.
 *
 * Note: true background blur (like CSS backdrop-filter) needs
 * Modifier.blur / a RenderEffect (Android 12+) or a library like
 * Haze if you want it to blur content underneath. This gives you the
 * flat "frosted card" look everywhere without that extra dependency.
 */
fun Modifier.glassPanel(
    cornerRadius: Int = 20,
    borderColor: Color = DevOutlineVariant,
    fillColor: Color = DevGlassFill
): Modifier = this
    .clip(RoundedCornerShape(cornerRadius.dp))
    .background(fillColor)

val DevGlassBorder = BorderStroke(1.dp, DevOutlineVariant)