package com.devlaunch.android.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Gradient used for the greeting headline text ("Good morning, Alex").
// Pale pastel colors read fine on the dark background, but the same
// pastel gradient is nearly invisible on a light background — so this
// switches to a deeper purple gradient in light mode.
val DevGreetingGradient: Brush
    @Composable get() = if (isAppInDarkTheme)
        Brush.linearGradient(colors = listOf(Color(0xFFAFC6FF), Color(0xFFC9BFFF)))
    else
        Brush.linearGradient(colors = listOf(Color(0xFF4C1D95), Color(0xFF7C3AED)))

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
 * Haze if you want the content underneath to actually blur. This gives
 * the flat "frosted card" look without that extra dependency.
 */
fun Modifier.glassPanel(
    cornerRadius: Int = 20,
    borderColor: Color = DevDarkOutline,
    fillColor: Color = DevGlassFill
): Modifier = this
    .clip(RoundedCornerShape(cornerRadius.dp))
    .background(fillColor)

val DevGlassBorder = BorderStroke(1.dp, DevDarkOutline)