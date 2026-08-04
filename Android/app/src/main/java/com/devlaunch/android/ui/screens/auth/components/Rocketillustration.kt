package com.devlaunch.android.ui.screens.auth.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun RocketIllustration(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Linear gradient for rocket engine flare
        val fireGradient = Brush.linearGradient(
            colors = listOf(Color(0xFFF59E0B), Color(0xFFEF4444), Color.Transparent),
            start = Offset(w * 0.5f, h * 0.5f),
            end = Offset(w * 0.5f, h * 0.95f)
        )

        // Draw Flare
        val firePath = Path().apply {
            moveTo(w * 0.45f, h * 0.65f)
            lineTo(w * 0.5f, h * 0.9f)
            lineTo(w * 0.5f, h * 0.9f)
            lineTo(w * 0.55f, h * 0.65f)
            close()
        }
        drawPath(firePath, brush = fireGradient)

        // Draw Rocket Fins (Blue #3B82F6)
        val leftFin = Path().apply {
            moveTo(w * 0.4f, h * 0.5f)
            lineTo(w * 0.25f, h * 0.65f)
            lineTo(w * 0.4f, h * 0.65f)
            close()
        }
        val rightFin = Path().apply {
            moveTo(w * 0.6f, h * 0.5f)
            lineTo(w * 0.75f, h * 0.65f)
            lineTo(w * 0.6f, h * 0.65f)
            close()
        }
        drawPath(leftFin, color = Color(0xFF3B82F6))
        drawPath(rightFin, color = Color(0xFF3B82F6))

        // Draw Rocket Body (Sleek Slate/White gradient)
        val bodyGradient = Brush.linearGradient(
            colors = listOf(Color(0xFFF8FAFC), Color(0xFFCBD5E1)),
            start = Offset(w * 0.5f, h * 0.15f),
            end = Offset(w * 0.5f, h * 0.65f)
        )
        val rocketBody = Path().apply {
            // Point nose
            moveTo(w * 0.5f, h * 0.15f)
            // Curving down to right base
            cubicTo(w * 0.6f, h * 0.3f, w * 0.6f, h * 0.5f, w * 0.6f, h * 0.65f)
            // Flat base
            lineTo(w * 0.4f, h * 0.65f)
            // Curving up to nose
            cubicTo(w * 0.4f, h * 0.5f, w * 0.4f, h * 0.3f, w * 0.5f, h * 0.15f)
            close()
        }
        drawPath(rocketBody, brush = bodyGradient)

        // Draw Nose cone (Purple #8B5CF6)
        val noseCone = Path().apply {
            moveTo(w * 0.5f, h * 0.15f)
            cubicTo(w * 0.55f, h * 0.22f, w * 0.57f, h * 0.28f, w * 0.57f, h * 0.3f)
            lineTo(w * 0.43f, h * 0.3f)
            cubicTo(w * 0.43f, h * 0.28f, w * 0.45f, h * 0.22f, w * 0.5f, h * 0.15f)
            close()
        }
        drawPath(noseCone, color = Color(0xFF8B5CF6))

        // Rocket window
        drawCircle(
            color = Color(0xFF0F172A),
            radius = w * 0.07f,
            center = Offset(w * 0.5f, h * 0.43f)
        )
        drawCircle(
            color = Color(0xFF38BDF8),
            radius = w * 0.05f,
            center = Offset(w * 0.5f, h * 0.43f)
        )

        // Draw an outer orbit ring for premium tech visual
        drawArc(
            color = Color(0x33F8FAFC),
            startAngle = -20f,
            sweepAngle = 220f,
            useCenter = false,
            topLeft = Offset(w * 0.1f, h * 0.1f),
            size = size * 0.8f,
            style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
        )
    }
}