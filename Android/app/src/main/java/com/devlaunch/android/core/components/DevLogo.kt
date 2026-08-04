package com.devlaunch.android.core.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DevLogo(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp
) {
    Canvas(
        modifier = modifier.size(size)
    ) {

        val width = size.toPx()
        val height = size.toPx()

        val gradient = Brush.linearGradient(
            colors = listOf(
                Color(0xFF818CF8),
                Color(0xFF6366F1),
                Color(0xFF4F46E5)
            ),
            start = Offset(0f, 0f),
            end = Offset(width, height)
        )

        // <
        val leftPath = Path().apply {
            moveTo(width * 0.35f, height * 0.25f)
            lineTo(width * 0.15f, height * 0.50f)
            lineTo(width * 0.35f, height * 0.75f)
        }

        // >
        val rightPath = Path().apply {
            moveTo(width * 0.65f, height * 0.25f)
            lineTo(width * 0.85f, height * 0.50f)
            lineTo(width * 0.65f, height * 0.75f)
        }

        // /
        val slashPath = Path().apply {
            moveTo(width * 0.58f, height * 0.18f)
            lineTo(width * 0.42f, height * 0.82f)
        }

        val strokeWidth = width * 0.08f

        drawPath(
            path = leftPath,
            brush = gradient,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )

        drawPath(
            path = rightPath,
            brush = gradient,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )

        drawPath(
            path = slashPath,
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFF38BDF8),
                    Color(0xFF0284C7)
                ),
                start = Offset(width * 0.5f, 0f),
                end = Offset(width * 0.5f, height)
            ),
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round
            )
        )
    }
}