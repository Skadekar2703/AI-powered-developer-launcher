package com.devlaunch.android.core.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun GoogleIcon(
    modifier: Modifier = Modifier,
    size: Dp = 18.dp
) {

    Canvas(
        modifier = modifier.size(size)
    ) {

        val width = size.toPx()
        val height = size.toPx()

        val scaleX = width / 24f
        val scaleY = height / 24f

        // Blue
        val bluePath = Path().apply {
            moveTo(22.56f * scaleX, 12.25f * scaleY)
            cubicTo(22.56f * scaleX, 11.47f * scaleY, 22.49f * scaleX, 10.72f * scaleY, 22.36f * scaleX, 10.0f * scaleY)
            lineTo(12f * scaleX, 10f * scaleY)
            lineTo(12f * scaleX, 14.26f * scaleY)
            lineTo(17.92f * scaleX, 14.26f * scaleY)
            cubicTo(17.66f * scaleX, 15.63f * scaleY, 16.88f * scaleX, 16.79f * scaleY, 15.71f * scaleX, 17.57f * scaleY)
            lineTo(15.71f * scaleX, 20.34f * scaleY)
            lineTo(19.28f * scaleX, 20.34f * scaleY)
            cubicTo(21.36f * scaleX, 18.42f * scaleY, 22.56f * scaleX, 15.60f * scaleY, 22.56f * scaleX, 12.25f * scaleY)
            close()
        }

        // Green
        val greenPath = Path().apply {
            moveTo(12f * scaleX, 23f * scaleY)
            cubicTo(14.97f * scaleX, 23f * scaleY, 17.46f * scaleX, 22.02f * scaleY, 19.28f * scaleX, 20.34f * scaleY)
            lineTo(15.71f * scaleX, 17.57f * scaleY)
            cubicTo(14.73f * scaleX, 18.23f * scaleY, 13.48f * scaleX, 18.63f * scaleY, 12f * scaleX, 18.63f * scaleY)
            cubicTo(9.14f * scaleX, 18.63f * scaleY, 6.71f * scaleX, 16.70f * scaleY, 5.84f * scaleX, 14.10f * scaleY)
            lineTo(2.18f * scaleX, 14.10f * scaleY)
            lineTo(2.18f * scaleX, 16.94f * scaleY)
            cubicTo(3.99f * scaleX, 20.53f * scaleY, 7.70f * scaleX, 23f * scaleY, 12f * scaleX, 23f * scaleY)
            close()
        }

        // Yellow
        val yellowPath = Path().apply {
            moveTo(5.84f * scaleX, 14.10f * scaleY)
            cubicTo(5.62f * scaleX, 13.44f * scaleY, 5.49f * scaleX, 12.74f * scaleY, 5.49f * scaleX, 12f * scaleY)
            cubicTo(5.49f * scaleX, 11.26f * scaleY, 5.62f * scaleX, 10.56f * scaleY, 5.84f * scaleX, 9.90f * scaleY)
            lineTo(5.84f * scaleX, 7.06f * scaleY)
            lineTo(2.18f * scaleX, 7.06f * scaleY)
            cubicTo(1.43f * scaleX, 8.55f * scaleY, 1f * scaleX, 10.22f * scaleY, 1f * scaleX, 12f * scaleY)
            cubicTo(1f * scaleX, 13.78f * scaleY, 1.43f * scaleX, 15.45f * scaleY, 2.18f * scaleX, 16.94f * scaleY)
            lineTo(5.84f * scaleX, 14.10f * scaleY)
            close()
        }

        // Red
        val redPath = Path().apply {
            moveTo(12f * scaleX, 5.38f * scaleY)
            cubicTo(13.62f * scaleX, 5.38f * scaleY, 15.06f * scaleX, 5.94f * scaleY, 16.21f * scaleX, 7.02f * scaleY)
            lineTo(19.36f * scaleX, 3.87f * scaleY)
            cubicTo(17.45f * scaleX, 2.09f * scaleY, 14.97f * scaleX, 1f * scaleY, 12f * scaleX, 1f * scaleY)
            cubicTo(7.70f * scaleX, 1f * scaleY, 3.99f * scaleX, 3.47f * scaleY, 2.18f * scaleX, 7.06f * scaleY)
            lineTo(5.84f * scaleX, 9.90f * scaleY)
            cubicTo(6.71f * scaleX, 7.30f * scaleY, 9.14f * scaleX, 5.38f * scaleY, 12f * scaleX, 5.38f * scaleY)
            close()
        }

        drawPath(bluePath, Color(0xFF4285F4))
        drawPath(greenPath, Color(0xFF34A853))
        drawPath(yellowPath, Color(0xFFFBBC05))
        drawPath(redPath, Color(0xFFEA4335))
    }
}