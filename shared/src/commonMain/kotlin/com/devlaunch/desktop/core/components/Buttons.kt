package com.devlaunch.desktop.core.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devlaunch.desktop.core.theme.DevBorder
import com.devlaunch.desktop.core.theme.DevOnPrimary
import com.devlaunch.desktop.core.theme.DevPrimary
import com.devlaunch.desktop.core.theme.DevPrimaryHover
import com.devlaunch.desktop.core.theme.DevNeutralBtn
import com.devlaunch.desktop.core.theme.DevTextPrimary

@Composable
fun DevButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isPressed by interactionSource.collectIsPressedAsState()

    // Smooth hover and press animations
    val backgroundColor by animateColorAsState(
        targetValue = when {
            !enabled -> DevPrimary.copy(alpha = 0.5f)
            isPressed -> DevPrimaryHover.copy(alpha = 0.9f)
            isHovered -> DevPrimaryHover
            else -> DevPrimary
        }
    )

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1.0f
    )

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .scale(scale),
        shape = RoundedCornerShape(8.dp),
        interactionSource = interactionSource,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = DevOnPrimary,
            disabledContainerColor = DevPrimary.copy(alpha = 0.4f),
            disabledContentColor = DevOnPrimary.copy(alpha = 0.6f)
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                letterSpacing = 0.2.sp
            )
        )
    }
}

@Composable
fun DevGoogleButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "Continue with Google"
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isPressed by interactionSource.collectIsPressedAsState()

    val backgroundColor by animateColorAsState(
        targetValue = when {
            isPressed -> Color(0xFF1E1E20)
            isHovered -> Color(0xFF242427)
            else -> DevNeutralBtn
        }
    )

    val borderColor by animateColorAsState(
        targetValue = if (isHovered) Color(0xFF3F3F46) else DevBorder
    )

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1.0f
    )

    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .scale(scale),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, borderColor),
        interactionSource = interactionSource,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = backgroundColor,
            contentColor = DevTextPrimary
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            GoogleIcon(size = 18.dp)
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    letterSpacing = 0.1.sp
                )
            )
        }
    }
}

@Composable
fun GoogleIcon(size: Dp = 18.dp) {
    Canvas(modifier = Modifier.size(size)) {
        val width = size.toPx()
        val height = size.toPx()
        val scaleX = width / 24f
        val scaleY = height / 24f

        // Blue segment
        val bluePath = Path().apply {
            moveTo(22.56f * scaleX, 12.25f * scaleY)
            cubicTo(22.56f * scaleX, 11.47f * scaleY, 22.49f * scaleX, 10.72f * scaleY, 22.36f * scaleX, 10.0f * scaleY)
            lineTo(12.0f * scaleX, 10.0f * scaleY)
            lineTo(12.0f * scaleX, 14.26f * scaleY)
            lineTo(17.92f * scaleX, 14.26f * scaleY)
            cubicTo(17.66f * scaleX, 15.63f * scaleY, 16.88f * scaleX, 16.79f * scaleY, 15.71f * scaleX, 17.57f * scaleY)
            lineTo(15.71f * scaleX, 20.34f * scaleY)
            lineTo(19.28f * scaleX, 20.34f * scaleY)
            cubicTo(21.36f * scaleX, 18.42f * scaleY, 22.56f * scaleX, 15.6f * scaleY, 22.56f * scaleX, 12.25f * scaleY)
            close()
        }

        // Green segment
        val greenPath = Path().apply {
            moveTo(12.0f * scaleX, 23.0f * scaleY)
            cubicTo(14.97f * scaleX, 23.0f * scaleY, 17.46f * scaleX, 22.02f * scaleY, 19.28f * scaleX, 20.34f * scaleY)
            lineTo(15.71f * scaleX, 17.57f * scaleY)
            cubicTo(14.73f * scaleX, 18.23f * scaleY, 13.48f * scaleX, 18.63f * scaleY, 12.0f * scaleX, 18.63f * scaleY)
            cubicTo(9.14f * scaleX, 18.63f * scaleY, 6.71f * scaleX, 16.7f * scaleY, 5.84f * scaleX, 14.1f * scaleY)
            lineTo(2.18f * scaleX, 14.1f * scaleY)
            lineTo(2.18f * scaleX, 16.94f * scaleY)
            cubicTo(3.99f * scaleX, 20.53f * scaleY, 7.7f * scaleX, 23.0f * scaleY, 12.0f * scaleX, 23.0f * scaleY)
            close()
        }

        // Yellow segment
        val yellowPath = Path().apply {
            moveTo(5.84f * scaleX, 14.1f * scaleY)
            cubicTo(5.62f * scaleX, 13.44f * scaleY, 5.49f * scaleX, 12.74f * scaleY, 5.49f * scaleX, 12.0f * scaleY)
            cubicTo(5.49f * scaleX, 11.26f * scaleY, 5.62f * scaleX, 10.56f * scaleY, 5.84f * scaleX, 9.9f * scaleY)
            lineTo(5.84f * scaleX, 7.06f * scaleY)
            lineTo(2.18f * scaleX, 7.06f * scaleY)
            cubicTo(1.43f * scaleX, 8.55f * scaleY, 1.0f * scaleX, 10.22f * scaleY, 1.0f * scaleX, 12.0f * scaleY)
            cubicTo(1.0f * scaleX, 13.78f * scaleY, 1.43f * scaleX, 15.45f * scaleY, 2.18f * scaleX, 16.94f * scaleY)
            lineTo(5.84f * scaleX, 14.1f * scaleY)
            close()
        }

        // Red segment
        val redPath = Path().apply {
            moveTo(12.0f * scaleX, 5.38f * scaleY)
            cubicTo(13.62f * scaleX, 5.38f * scaleY, 15.06f * scaleX, 5.94f * scaleY, 16.21f * scaleX, 7.02f * scaleY)
            lineTo(19.36f * scaleX, 3.87f * scaleY)
            cubicTo(17.45f * scaleX, 2.09f * scaleY, 14.97f * scaleX, 1.0f * scaleY, 12.0f * scaleX, 1.0f * scaleY)
            cubicTo(7.7f * scaleX, 1.0f * scaleY, 3.99f * scaleX, 3.47f * scaleY, 2.18f * scaleX, 7.06f * scaleY)
            lineTo(5.84f * scaleX, 9.9f * scaleY)
            cubicTo(6.71f * scaleX, 7.3f * scaleY, 9.14f * scaleX, 5.38f * scaleY, 12.0f * scaleX, 5.38f * scaleY)
            close()
        }

        // Draw segments with Google brand colors
        drawPath(bluePath, color = Color(0xFF4285F4))
        drawPath(greenPath, color = Color(0xFF34A853))
        drawPath(yellowPath, color = Color(0xFFFBBC05))
        drawPath(redPath, color = Color(0xFFEA4335))
    }
}
