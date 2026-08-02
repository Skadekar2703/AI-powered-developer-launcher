package com.devlaunch.desktop.core.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devlaunch.desktop.core.theme.DevBorder
import com.devlaunch.desktop.core.theme.DevInputBg
import com.devlaunch.desktop.core.theme.DevPrimary
import com.devlaunch.desktop.core.theme.DevTextMuted
import com.devlaunch.desktop.core.theme.DevTextPrimary
import com.devlaunch.desktop.core.theme.DevTextSecondary

@Composable
fun DevTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    error: String? = null,
    isPassword: Boolean = false,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        // Label
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium,
                color = if (error != null) MaterialTheme.colorScheme.error else DevTextSecondary
            ),
            modifier = Modifier.padding(bottom = 6.dp)
        )

        // Text Field
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = DevTextMuted,
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            singleLine = true,
            enabled = enabled,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            isError = error != null,
            visualTransformation = if (isPassword && !passwordVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            keyboardOptions = if (isPassword) {
                keyboardOptions.copy(keyboardType = KeyboardType.Password)
            } else {
                keyboardOptions
            },
            trailingIcon = if (isPassword) {
                {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        EyeIcon(
                            visible = passwordVisible,
                            tint = DevTextSecondary
                        )
                    }
                }
            } else null,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = DevTextPrimary,
                unfocusedTextColor = DevTextPrimary,
                disabledTextColor = DevTextSecondary,
                focusedContainerColor = DevInputBg,
                unfocusedContainerColor = DevInputBg,
                disabledContainerColor = DevInputBg,
                errorContainerColor = DevInputBg,
                focusedBorderColor = DevPrimary,
                unfocusedBorderColor = DevBorder,
                disabledBorderColor = DevBorder.copy(alpha = 0.5f),
                errorBorderColor = MaterialTheme.colorScheme.error,
                cursorColor = DevPrimary,
                errorCursorColor = MaterialTheme.colorScheme.error
            ),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 14.sp
            )
        )

        // Error Message
        AnimatedVisibility(
            visible = error != null,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            error?.let {
                Column {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun EyeIcon(
    visible: Boolean,
    tint: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(20.dp)) {
        val w = size.width
        val h = size.height

        // Draw Eye Outline
        val eyePath = Path().apply {
            moveTo(w * 0.1f, h * 0.5f)
            cubicTo(w * 0.3f, h * 0.2f, w * 0.7f, h * 0.2f, w * 0.9f, h * 0.5f)
            cubicTo(w * 0.7f, h * 0.8f, w * 0.3f, h * 0.8f, w * 0.1f, h * 0.5f)
            close()
        }
        drawPath(
            path = eyePath,
            color = tint,
            style = Stroke(width = 1.5.dp.toPx(), cap = StrokeCap.Round)
        )

        // Draw Pupil
        drawCircle(
            color = tint,
            radius = w * 0.14f,
            center = Offset(w * 0.5f, h * 0.5f)
        )

        // Draw slash if not visible
        if (!visible) {
            drawLine(
                color = tint,
                start = Offset(w * 0.25f, h * 0.25f),
                end = Offset(w * 0.75f, h * 0.75f),
                strokeWidth = 1.5.dp.toPx(),
                cap = StrokeCap.Round
            )
        }
    }
}
